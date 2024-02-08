package com.carterprojects.movienightmanager.model.nomination;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class NominationRequest {
    @Setter
    Integer segmentId;
    Integer movieId;
    String movieTitle;
    String watchDate = "1/10/2024";
    Integer userId;
    String posterPath;
    String overview;
    String releaseDate;
    List<String> genres;
    Integer runtime;
    Integer communityId;
}
