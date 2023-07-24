package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.dto.MovieNightSegmentDto;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import java.util.stream.Collectors;

public class MovieNightSegmentMapper {
    public static MovieNightSegmentDto segmentToSegmentDto(MovieNightSegment segment) {
        return MovieNightSegmentDto.builder()
                .id(segment.getId())
                .nominationStartDate(segment.getNominationStartDate())
                .nominationLockDate(segment.getNominationLockDate())
                .chosenWatchDate(segment.getChosenWatchDate())
                .segmentEndDate(segment.getSegmentEndDate())
                .watchType(segment.getWatchType())
                .nominations(segment.getNominations().stream().map(NominationsMapper::nominationToNominationWithLikesDto).collect(Collectors.toList()))
                .build();
    }
}
