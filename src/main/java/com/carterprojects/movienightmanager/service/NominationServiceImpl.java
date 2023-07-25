package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.nomination.NominationRequest;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import com.carterprojects.movienightmanager.repository.NominationRepository;
import com.carterprojects.movienightmanager.repository.models.Nomination;
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
    MovieNightSegmentService movieNightSegmentServiceImpl;
    @Autowired
    NominationLikeService nominationLikeServiceImpl;
    @Autowired
    NominationRepository nominationRepository;
    @Autowired
    AppUserRepository appUserRepository;

    public List<Nomination> getAllNominationsByCurrentSegment() {
        var currentSegment = movieNightSegmentServiceImpl.getCurrentMovieNightSegment();
        return currentSegment.map(seg -> nominationRepository.findAllByMovieNightSegment_Id(seg.getId())).orElse(Collections.emptyList());
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

        var currentSegment = movieNightSegmentServiceImpl.getMovieNightSegmentById(nominationRequest.getSegmentId())
        .orElseThrow(
            () -> {
                log.error("Could not create nomination because segment with id: {} was not found", nominationRequest.getSegmentId());
                return new MnmAppException("Could not create nomination because the segment was not found");
            }
        );

        var newNomination = Nomination.builder()
                .movieTitle(nominationRequest.getMovieTitle())
                .chosen(false)
                .posterPath(nominationRequest.getPosterPath())
                .movieNightSegment(currentSegment)
                .user(user)
                .build();

        newNomination = nominationRepository.save(newNomination);

        var nominationLike =
                nominationLikeServiceImpl.createNominationLike(
                        newNomination,
                        user,
                        nominationRequest.getWatchType(),
                        LocalDateTime.parse(nominationRequest.getWatchDate())
                );

        newNomination.setNominationLikes(List.of(nominationLike));
        return newNomination;
    }

}
