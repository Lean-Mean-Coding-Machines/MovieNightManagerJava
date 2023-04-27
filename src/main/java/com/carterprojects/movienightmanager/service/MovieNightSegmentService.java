package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface MovieNightSegmentService {

    Optional<MovieNightSegment> getCurrentMovieNightSegment();

    List<MovieNightSegment> getPreviousMovieNightSegments(Integer currentID, Integer numSegments);

    Optional<MovieNightSegment> getMovieNightSegmentById(Integer id);

    MovieNightSegment saveNewMovieNightSegment(LocalDateTime segmentStart);

}
