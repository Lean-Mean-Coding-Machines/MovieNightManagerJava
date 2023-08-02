package com.carterprojects.movienightmanager.model.nomination;

import com.carterprojects.movienightmanager.repository.models.WatchType;
import lombok.Getter;

@Getter
public class NominationLikeRequest {
    Integer nominationId;
    WatchType watchType;
    String watchDate;
    Integer userId;
}