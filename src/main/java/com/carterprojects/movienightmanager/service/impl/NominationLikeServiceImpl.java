package com.carterprojects.movienightmanager.service.impl;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.nomination.NominationLikeRequest;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import com.carterprojects.movienightmanager.repository.NominationLikeRepository;
import com.carterprojects.movienightmanager.repository.NominationRepository;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import com.carterprojects.movienightmanager.repository.models.NominationLike;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;
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
    public NominationLike createNominationLike(Nomination nomination, AppUser user,
                                               LocalDateTime watchDate) {
        var nominationLike = NominationLike.builder()
                .preferredWatchDate(watchDate)
                .enabled(true)
                .user(user)
                .nomination(nomination)
                .build();

        return nominationLikeRepository.save(nominationLike);
    }

    @Override
    public NominationLike manageNominationLikeFromRequest(NominationLikeRequest likeRequest) throws MnmAppException {
        var user = appUserRepository.findById(likeRequest.getUserId())
                .orElseThrow(() -> {
                    log.error("Could create nomination like because user with id: {} was not found", likeRequest.getUserId());
                    return new MnmAppException("Could create nomination like because user was not found");
                });

        var nomination = nominationRepository.findById(likeRequest.getNominationId())
                .orElseThrow(() -> {
                    log.error("Could create nomination like because nomination with id: {} was not found", likeRequest.getNominationId());
                    return new MnmAppException("Could create nomination like because the nomination was not found");
                });

        var nominationLike = nominationLikeRepository.findByNomination_IdAndUser_Id(likeRequest.getNominationId(), likeRequest.getUserId())
                .map(nomLike -> {
                    nomLike.setEnabled(!nomLike.getEnabled());
                    return nomLike;
                })
                .orElseGet(() -> NominationLike.builder()
                        .enabled(true)
                        .user(user)
                        .nomination(nomination)
                        .build()
                );

        return nominationLikeRepository.save(nominationLike);
    }
}
