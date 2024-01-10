package com.carterprojects.movienightmanager.model.nomination;
import java.util.List;

import com.carterprojects.movienightmanager.repository.models.WatchType;
import lombok.Getter;

@Getter
public class NominationRequest {
    Integer segmentId;
    Integer movieId;
    String movieTitle;
    WatchType watchType = WatchType.ANY;
    String watchDate = "1/10/2024";
    Integer userId;
    String posterPath;
    String overview;
    String releaseDate;
    List<String> genres;
    Integer runtime;
}
