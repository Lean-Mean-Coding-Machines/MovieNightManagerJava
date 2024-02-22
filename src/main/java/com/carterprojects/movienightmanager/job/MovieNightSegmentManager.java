package com.carterprojects.movienightmanager.job;

import com.carterprojects.movienightmanager.service.MovieNightSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class MovieNightSegmentManager {

    @Autowired
    MovieNightSegmentService movieNightSegmentServiceImpl;
}