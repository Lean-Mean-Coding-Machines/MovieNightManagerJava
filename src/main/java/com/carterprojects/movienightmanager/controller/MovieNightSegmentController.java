package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.mapper.MovieNightSegmentMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.service.MovieNightSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/segment")
public class MovieNightSegmentController {
    @Autowired
    MovieNightSegmentService movieNightSegmentServiceImpl;

    @GetMapping("current")
    public MnmApiResponse getCurrentMovieNightSegment() {
        return movieNightSegmentServiceImpl
                .getCurrentMovieNightSegment()
                .map(segment -> MnmApiResponse.success(MovieNightSegmentMapper.segmentToSegmentDto(segment)))
                .orElse(MnmApiResponse.notFound());
    }
}