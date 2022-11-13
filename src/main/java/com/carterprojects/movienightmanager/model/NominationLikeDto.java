package com.carterprojects.movienightmanager.model;

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

    String preferredWatchType;

    String preferredWatchDate;

    public static class NominationLikeWithNominationDto extends NominationLikeDto {
        NominationDto nomination;
    }
}