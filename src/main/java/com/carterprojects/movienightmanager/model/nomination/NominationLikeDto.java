package com.carterprojects.movienightmanager.model.nomination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NominationLikeDto {
    Integer id;

    Boolean enabled;

    String preferredWatchDate;

    Integer userId;

    String username;

    public static class NominationLikeWithNominationDto extends NominationLikeDto {
        NominationDto nomination;
    }
}