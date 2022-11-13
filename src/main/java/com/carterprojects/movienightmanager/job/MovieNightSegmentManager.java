package com.carterprojects.movienightmanager.job;

import com.carterprojects.movienightmanager.service.MovieNightSegmentService;
import com.carterprojects.movienightmanager.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class MovieNightSegmentManager {

    @Autowired
    MovieNightSegmentService movieNightSegmentServiceImpl;

    @Scheduled(fixedDelay = 86400000) // Every 24 hrs
    public void CheckForNewSegment() {
        var utcNow = DateUtil.getDateTimeUtc();
        var currentSegment = movieNightSegmentServiceImpl.getCurrentMovieNightSegment();
        if (currentSegment == null) {
            movieNightSegmentServiceImpl.saveNewMovieNightSegment(LocalDateTime.now());
        } else if (utcNow.isAfter(currentSegment.getNominationLockDate()) && utcNow.isBefore(currentSegment.getSegmentEndDate())) {
            movieNightSegmentServiceImpl.saveNewMovieNightSegment(currentSegment.getSegmentEndDate());
        }
    }
}