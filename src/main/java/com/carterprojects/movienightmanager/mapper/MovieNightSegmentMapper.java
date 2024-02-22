package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.dto.MovieNightSegmentDto;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;

import java.util.Collections;

public class MovieNightSegmentMapper {
    public static MovieNightSegmentDto segmentToSegmentDto(MovieNightSegment segment) {
        return MovieNightSegmentDto.builder()
                .id(segment.getId())
                .nominationStartDate(segment.getNominationStartDate())
                .nominationLockDate(segment.getNominationLockDate())
                .chosenWatchDate(segment.getChosenWatchDate())
                .segmentEndDate(segment.getSegmentEndDate())
                .nominations(
                    segment.getNominations() == null ?
                    Collections.emptyList() :
                    segment.getNominations()
                        .stream()
                        .map(NominationsMapper::nominationToNominationWithLikesDto)
                        .toList()
                )
                .build();
    }
}
