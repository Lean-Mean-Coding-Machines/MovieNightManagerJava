package com.carterprojects.movienightmanager.model.dto;

import com.carterprojects.movienightmanager.model.nomination.NominationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieNightSegmentRequestDto {

    LocalDateTime nominationLockDate;
    LocalDateTime chosenWatchDate;
    Integer communityId;
    Integer userId;
    NominationRequest nomination;

}
