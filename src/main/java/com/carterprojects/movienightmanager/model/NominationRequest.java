package com.carterprojects.movienightmanager.model;

import com.carterprojects.movienightmanager.repository.models.WatchType;
import lombok.Getter;

@Getter
public class NominationRequest {
    String movieTitle;
    WatchType watchType;
    String watchDate;
    Integer userId;
}
