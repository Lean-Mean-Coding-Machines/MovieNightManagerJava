package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.service.MovieNightSegmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/segment")
public class MovieNightSegmentController {

    MovieNightSegmentService movieNightSegmentServiceImpl;

    @GetMapping("current")
    public MnmApiResponse getCurrentMovieNightSegment() {
        var currentSegment = movieNightSegmentServiceImpl.getCurrentMovieNightSegment();
        if (currentSegment == null) {
            return MnmApiResponse.notFound();
        }
        return MnmApiResponse.success(currentSegment);
    }

}
