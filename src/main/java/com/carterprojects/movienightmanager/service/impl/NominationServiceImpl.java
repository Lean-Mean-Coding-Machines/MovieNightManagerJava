package com.carterprojects.movienightmanager.service.impl;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.nomination.NominationRequest;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import com.carterprojects.movienightmanager.repository.MovieNightSegmentRepository;
import com.carterprojects.movienightmanager.repository.NominationRepository;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import com.carterprojects.movienightmanager.service.NominationLikeService;
import com.carterprojects.movienightmanager.service.NominationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class NominationServiceImpl implements NominationService {
    @Autowired
    MovieNightSegmentRepository movieNightSegmentRepository;
    @Autowired
    NominationLikeService nominationLikeServiceImpl;
    @Autowired
    NominationRepository nominationRepository;
    @Autowired
    AppUserRepository appUserRepository;

    public List<Nomination> getAllNominationsBySegmentId(Integer segmentId) {
        return movieNightSegmentRepository.findById(segmentId).map(MovieNightSegment::getNominations).orElse(Collections.emptyList());
    }

    public List<Nomination> getAllNominationsByUserId(Integer userID) {
        return nominationRepository.findAllByUser_Id(userID);
    }

    public Nomination createNominationFromRequest(NominationRequest nominationRequest) throws MnmAppException {
        var user = appUserRepository.findById(nominationRequest.getUserId())
                .orElseThrow(
                        () -> {
                            log.error("Could not create nomination because user with id: {} was not found", nominationRequest.getUserId());
                            return new MnmAppException("Could create nomination because the user was not found");
                        }
                );

        var currentSegment = movieNightSegmentRepository.findById(nominationRequest.getSegmentId())
                .orElseThrow(
                        () -> {
                            log.error("Could not create nomination because segment with id: {} was not found", nominationRequest.getSegmentId());
                            return new MnmAppException("Could not create nomination because the segment was not found");
                        }
                );

        return saveNewNomination(nominationRequest, currentSegment, user);
    }

    public Nomination saveNewNomination(NominationRequest nominationRequest, MovieNightSegment segment, AppUser user) {
        var newNomination = Nomination.builder()
                .movieId(nominationRequest.getMovieId())
                .movieTitle(nominationRequest.getMovieTitle())
                .chosen(false)
                .posterPath(nominationRequest.getPosterPath())
                .movieOverview(nominationRequest.getOverview())
                .releaseDate(nominationRequest.getReleaseDate())
                .genres(String.join(",", nominationRequest.getGenres()))
                .runtime(nominationRequest.getRuntime())
                .movieNightSegment(segment)
                .user(user)
                .build();

        newNomination = nominationRepository.save(newNomination);

        var nominationLike =
                nominationLikeServiceImpl.createNominationLike(
                        newNomination,
                        user,
                        LocalDateTime.now()
                );

        newNomination.setNominationLikes(List.of(nominationLike));
        return newNomination;
    }

    public void deleteNomination(Integer nominationId, Integer userId, Integer segmentId) throws MnmAppException {
        var currentSegment = movieNightSegmentRepository.getCurrentMovieNightSegmentById(LocalDateTime.now(), segmentId)
                .orElseThrow(
                        () -> {
                            var errorStr = "No current segment found. Cannot delete nomination from a previous segment";
                            log.error(errorStr);
                            return new MnmAppException(errorStr);
                        }
                );

        var nomination = nominationRepository.findByUser_IdAndIdAndMovieNightSegment_Id(userId, nominationId, segmentId)
                .orElseThrow(
                        () -> {
                            var errorStr = String.format(
                                    "No nomination found for the current segment: %d with user id: %d and nomination id: %d",
                                    currentSegment.getId(), userId, nominationId
                            );
                            log.error(errorStr);
                            return new MnmAppException(errorStr);
                        }
                );

        try {
            nominationRepository.delete(nomination);
        } catch (Exception ex) {
            log.error("Could not delete exception", ex);
            throw new MnmAppException("Error while deleting nomination, please try again later");
        }
    }

}
