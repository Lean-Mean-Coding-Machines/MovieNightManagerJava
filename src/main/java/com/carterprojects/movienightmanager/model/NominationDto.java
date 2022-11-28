package com.carterprojects.movienightmanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NominationDto {

    Integer id;
    String movieTitle;
    Boolean chosen;
    String submittedBy;

    @Data
    @NoArgsConstructor
    public static class NominationWithLikesDto extends NominationDto {
        List<NominationLikeDto> nominationLikes;
    }
}
