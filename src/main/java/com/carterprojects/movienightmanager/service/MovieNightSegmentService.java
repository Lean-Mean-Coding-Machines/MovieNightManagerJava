package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.dto.MovieNightSegmentRequestDto;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface MovieNightSegmentService {

    Optional<MovieNightSegment> getCurrentMovieNightSegmentForCommunity(Integer communityId);

    List<MovieNightSegment> getPreviousMovieNightSegments(Integer currentID, Integer numSegments);

    Optional<MovieNightSegment> getMovieNightSegmentById(Integer id);

    MovieNightSegment createNewMovieNightSegment(MovieNightSegmentRequestDto segmentRequest) throws MnmAppException;

}
