package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovieNightSegmentService {

    Optional<MovieNightSegment> getCurrentMovieNightSegment();

    List<MovieNightSegment> getPreviousMovieNightSegments(Integer currentID, Integer numSegments);

    Optional<MovieNightSegment> getMovieNightSegmentById(Integer id);

    MovieNightSegment saveNewMovieNightSegment(LocalDateTime segmentStart);

}
