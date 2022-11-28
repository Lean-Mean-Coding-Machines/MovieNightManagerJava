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
        currentSegment.ifPresentOrElse(
                seg -> {
                    if (utcNow.isAfter(seg.getNominationLockDate()) && utcNow.isBefore(seg.getSegmentEndDate())) {
                        movieNightSegmentServiceImpl.saveNewMovieNightSegment(seg.getSegmentEndDate());
                    }
                },
                () -> {
                    movieNightSegmentServiceImpl.saveNewMovieNightSegment(LocalDateTime.now());
                }
        );
    }
}