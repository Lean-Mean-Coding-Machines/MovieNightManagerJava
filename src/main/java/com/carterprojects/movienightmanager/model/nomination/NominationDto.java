package com.carterprojects.movienightmanager.model.nomination;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NominationDto {

    Integer id;
    Integer movieId;
    String movieTitle;
    Boolean chosen;
    String submittedBy;
    String posterPath;
    String movieOverview;
    String releaseDate;
    Integer runtime;
    List<String> genres;

    // List<String> userLikes;
    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    public static class NominationWithLikesDto extends NominationDto {
        List<NominationLikeDto> nominationLikes;
    }
}
