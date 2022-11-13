package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.model.NominationLikeRequest;
import com.carterprojects.movienightmanager.repository.models.AppUser;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import com.carterprojects.movienightmanager.repository.models.NominationLike;
import com.carterprojects.movienightmanager.repository.models.WatchType;

import java.time.LocalDateTime;
import java.util.List;

public interface NominationLikeService {
    List<NominationLike> getAllNominationLikesByUserId(Integer userId);
    List<NominationLike> getAllNominationLikesByNominationId(Integer nominationId);
    NominationLike createNominationLike(Nomination nomination, AppUser user, WatchType watchType, LocalDateTime watchDate);
    NominationLike createNominationLikeFromRequest(NominationLikeRequest likeRequest);
}
