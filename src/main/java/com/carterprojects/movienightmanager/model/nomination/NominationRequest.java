package com.carterprojects.movienightmanager.model.nomination;

import com.carterprojects.movienightmanager.repository.models.WatchType;
import lombok.Getter;
import lombok.Setter;

@Getter
public class NominationRequest {
    @Setter
    Integer segmentId;
    String movieTitle;
    WatchType watchType;
    String watchDate;
    Integer userId;
    String posterPath;
    String overview;
    String releaseDate;
    Integer communityId;
}
