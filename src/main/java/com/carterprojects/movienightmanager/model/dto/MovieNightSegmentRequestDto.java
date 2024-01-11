package com.carterprojects.movienightmanager.model.dto;

import com.carterprojects.movienightmanager.model.nomination.NominationRequest;
import com.carterprojects.movienightmanager.repository.models.WatchType;
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

    LocalDateTime nominationStartDate;
    LocalDateTime nominationLockDate;
    LocalDateTime chosenWatchDate;
    WatchType watchType;
    Integer communityId;
    Integer userId;
    NominationRequest nomination;

}
