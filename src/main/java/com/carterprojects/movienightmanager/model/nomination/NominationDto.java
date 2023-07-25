package com.carterprojects.movienightmanager.model.nomination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    String posterPath;

    @Data
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper=false)
    public static class NominationWithLikesDto extends NominationDto {
        List<NominationLikeDto> nominationLikes;
    }
}
