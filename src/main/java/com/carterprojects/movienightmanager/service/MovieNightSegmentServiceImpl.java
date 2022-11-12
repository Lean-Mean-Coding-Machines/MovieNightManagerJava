package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.repository.MovieNightSegmentRepository;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovieNightSegmentServiceImpl implements MovieNightSegmentService {

    MovieNightSegmentRepository movieNightSegmentRepository;

    public MovieNightSegment getCurrentMovieNightSegment() {
        return movieNightSegmentRepository.getMovieNightSegmentByDate(LocalDateTime.now());
    }

}
