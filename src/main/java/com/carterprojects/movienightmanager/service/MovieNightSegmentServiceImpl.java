package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.constant.MovieNightSegmentConstants;
import com.carterprojects.movienightmanager.repository.MovieNightSegmentRepository;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import com.carterprojects.movienightmanager.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter.*;
import java.time.temporal.*;
import java.util.Optional;
import java.io.*;

@Service
public class MovieNightSegmentServiceImpl implements MovieNightSegmentService {

    @Autowired
    MovieNightSegmentRepository movieNightSegmentRepository;

    public Optional<MovieNightSegment> getCurrentMovieNightSegment() {
        return movieNightSegmentRepository.getMovieNightSegmentByDateTime(DateUtil.getDateTimeUtc());
    }

    public Optional<MovieNightSegment> getMovieNightSegmentByDate(String date) {
        System.out.print(date);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        System.out.print("\n\n\n\n\n");
        TemporalAccessor ta = formatter.parse("2022-12-12T00:00:00");
        System.out.print(ta);
        System.out.print(LocalDateTime.from(ta));
        System.out.print("\n\n\n\n\n");
        return movieNightSegmentRepository.getMovieNightSegmentByDateTime(LocalDateTime.from(ta));
    }

    public Optional<MovieNightSegment> getMovieNightSegmentById(Integer id) {
        return movieNightSegmentRepository.findById(id);
    }

    public MovieNightSegment saveNewMovieNightSegment(LocalDateTime segmentStart) {
        var newSegment = MovieNightSegment.builder()
                .nominationStartDate(segmentStart)
                .nominationLockDate(segmentStart.plusDays(MovieNightSegmentConstants.NOMINATION_DURATION_DAYS))
                .segmentEndDate(segmentStart.plusDays(MovieNightSegmentConstants.SEGMENT_DURATION_DAYS))
                .chosenWatchDate(null)
                .watchType(null)
                .build();

        return movieNightSegmentRepository.save(newSegment);
    }

}
