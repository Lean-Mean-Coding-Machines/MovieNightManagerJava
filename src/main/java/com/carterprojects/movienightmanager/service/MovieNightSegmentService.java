package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;

import java.time.LocalDateTime;

public interface MovieNightSegmentService {

    MovieNightSegment getCurrentMovieNightSegment();
    MovieNightSegment saveNewMovieNightSegment(LocalDateTime segmentStart);

}
