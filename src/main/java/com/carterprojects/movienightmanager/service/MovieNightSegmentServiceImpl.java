package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.constant.MovieNightSegmentConstants;
import com.carterprojects.movienightmanager.repository.MovieNightSegmentRepository;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import com.carterprojects.movienightmanager.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovieNightSegmentServiceImpl implements MovieNightSegmentService {

    @Autowired
    MovieNightSegmentRepository movieNightSegmentRepository;

    public MovieNightSegment getCurrentMovieNightSegment() {
        return movieNightSegmentRepository.getMovieNightSegmentByDate(DateUtil.getDateTimeUtc());
    }

    public MovieNightSegment saveNewMovieNightSegment(LocalDateTime segmentStart) {
        var newSegment =
                MovieNightSegment.builder()
                        .nominationStartDate(segmentStart)
                        .nominationLockDate(segmentStart.plusDays(MovieNightSegmentConstants.NOMINATION_DURATION_DAYS))
                        .segmentEndDate(segmentStart.plusDays(MovieNightSegmentConstants.SEGMENT_DURATION_DAYS))
                        .chosenWatchDate(null)
                        .watchType(null)
                        .build();

        return movieNightSegmentRepository.save(newSegment);
    }

}
