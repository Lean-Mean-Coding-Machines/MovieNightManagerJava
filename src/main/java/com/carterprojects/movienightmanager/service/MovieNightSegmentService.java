package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;

import java.time.LocalDateTime;
import java.util.Optional;

public interface MovieNightSegmentService {

    Optional<MovieNightSegment> getCurrentMovieNightSegment();

    Optional<MovieNightSegment> getMovieNightSegmentById(Integer id);

    Optional<MovieNightSegment> getMovieNightSegmentByDate(String date);

    MovieNightSegment saveNewMovieNightSegment(LocalDateTime segmentStart);

}
