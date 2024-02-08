package com.carterprojects.movienightmanager.model.dto;

import com.carterprojects.movienightmanager.model.nomination.NominationDto;
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
    LocalDateTime segmentEndDate;
    List<NominationDto.NominationWithLikesDto> nominations;

}
