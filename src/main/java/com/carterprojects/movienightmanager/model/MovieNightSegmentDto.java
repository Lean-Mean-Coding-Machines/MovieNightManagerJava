package com.carterprojects.movienightmanager.model;

import com.carterprojects.movienightmanager.repository.models.WatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieNightSegmentDto {

    Integer id;
    LocalDateTime nominationStartDate;
    LocalDateTime nominationLockDate;
    LocalDateTime chosenWatchDate;
    WatchType watchType;
    LocalDateTime segmentEndDate;
    List<NominationDto.NominationWithLikesDto> nominations;

}
