package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.model.NominationRequest;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import com.carterprojects.movienightmanager.repository.NominationRepository;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return nominationRepository.findAllByMovieNightSegment_Id(currentSegment.getId());
    }

    public List<Nomination> getAllNominationsByUserId(Integer userID) {
        return nominationRepository.findAllByUser_Id(userID);
    }

    public Nomination createNominationFromRequest(NominationRequest nominationRequest) {
        var user = appUserRepository.findById(nominationRequest.getUserId());
        if (user.isEmpty()) {
            log.error("Could create nomination because user with id: {} was not found", nominationRequest.getUserId());
            return null;
        }

        var currentSegment = movieNightSegmentServiceImpl.getCurrentMovieNightSegment();

        var newNomination = Nomination.builder()
                .movieTitle(nominationRequest.getMovieTitle())
                .chosen(false)
                .movieNightSegment(currentSegment)
                .user(user.get())
                .build();

        newNomination = nominationRepository.save(newNomination);

        var nominationLike =
                nominationLikeServiceImpl.createNominationLike(
                        newNomination,
                        user.get(),
                        nominationRequest.getWatchType(),
                        LocalDateTime.parse(nominationRequest.getWatchDate())
                );

        newNomination.setNominationLikes(List.of(nominationLike));
        return newNomination;
    }

}
