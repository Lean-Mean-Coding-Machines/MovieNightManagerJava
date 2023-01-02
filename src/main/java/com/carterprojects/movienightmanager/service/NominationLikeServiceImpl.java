package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.model.NominationLikeRequest;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import com.carterprojects.movienightmanager.repository.NominationLikeRepository;
import com.carterprojects.movienightmanager.repository.NominationRepository;
import com.carterprojects.movienightmanager.repository.models.AppUser;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import com.carterprojects.movienightmanager.repository.models.NominationLike;
import com.carterprojects.movienightmanager.repository.models.WatchType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class NominationLikeServiceImpl implements NominationLikeService {
    @Autowired
    NominationLikeRepository nominationLikeRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    NominationRepository nominationRepository;

    @Override
    public List<NominationLike> getAllNominationLikesByUserId(Integer userId) {
        return nominationLikeRepository.findNominationLikesByUser_Id(userId);
    }

    @Override
    public List<NominationLike> getAllNominationLikesByNominationId(Integer nominationId) {
        return nominationLikeRepository.findNominationLikesByNomination_Id(nominationId);
    }

    @Override
    public NominationLike createNominationLike(Nomination nomination, AppUser user, WatchType watchType,
            LocalDateTime watchDate) {
        var nominationLike = NominationLike.builder()
                .preferredWatchType(watchType)
                .preferredWatchDate(watchDate)
                .enabled(true)
                .user(user)
                .nomination(nomination)
                .build();

        return nominationLikeRepository.save(nominationLike);
    }

    @Override
    public NominationLike manageNominationLikeFromRequest(NominationLikeRequest likeRequest) {
        var user = appUserRepository.findById(likeRequest.getUserId());
        if (user.isEmpty()) {
            log.error("Could create nomination like because user with id: {} was not found", likeRequest.getUserId());
            return null;
        }

        var nomination = nominationRepository.findById(likeRequest.getNominationId());
        if (nomination.isEmpty()) {
            log.error("Could create nomination like because nomination with id: {} was not found",
                    likeRequest.getNominationId());
            return null;
        }

        var nominationLike = nominationLikeRepository.findByNomination_IdAndUser_Id(likeRequest.getNominationId(),
                likeRequest.getUserId());
        NominationLike nominationLikeObj = null;
        if (nominationLike.isEmpty()) {
            nominationLikeObj = NominationLike.builder()
                    .preferredWatchType(likeRequest.getWatchType())
                    .preferredWatchDate(LocalDateTime.parse(likeRequest.getWatchDate()))
                    .enabled(true)
                    .user(user.get())
                    .nomination(nomination.get())
                    .build();
        } else {
            nominationLikeObj = nominationLike.get();
            nominationLikeObj.setEnabled(!nominationLikeObj.getEnabled());
            if (nominationLikeObj.getEnabled()) {
                nominationLikeObj.setPreferredWatchType(likeRequest.getWatchType());
                nominationLikeObj.setPreferredWatchDate(LocalDateTime.parse(likeRequest.getWatchDate()));
            }
        }
        return nominationLikeRepository.save(nominationLikeObj);
    }
}
