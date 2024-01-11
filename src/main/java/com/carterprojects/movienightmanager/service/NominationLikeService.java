package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.nomination.NominationLikeRequest;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import com.carterprojects.movienightmanager.repository.models.NominationLike;
import com.carterprojects.movienightmanager.repository.models.WatchType;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;

import java.time.LocalDateTime;
import java.util.List;

public interface NominationLikeService {
    List<NominationLike> getAllNominationLikesByUserId(Integer userId);

    List<NominationLike> getAllNominationLikesByNominationId(Integer nominationId);

    NominationLike createNominationLike(Nomination nomination, AppUser user, WatchType watchType, LocalDateTime watchDate);

    NominationLike manageNominationLikeFromRequest(NominationLikeRequest likeRequest) throws MnmAppException;
}
