package com.carterprojects.movienightmanager.model.nomination;

import com.carterprojects.movienightmanager.repository.models.WatchType;
import lombok.Getter;

@Getter
public class NominationRequest {
    Integer segmentId;
    Integer movieId;
    String movieTitle;
    WatchType watchType;
    String watchDate;
    Integer userId;
    String posterPath;
    String overview;
    String releaseDate;
}
