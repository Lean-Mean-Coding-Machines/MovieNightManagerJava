package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.mapper.MovieNightSegmentMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.service.MovieNightSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping(path = "date", consumes = { "text/plain", "application/json" }, produces = "text/plain")
    public MnmApiResponse getMovieSegmentByDate(@RequestBody String date) {
        return MnmApiResponse.success(
                movieNightSegmentServiceImpl.getMovieNightSegmentByDate(date)
                        .stream()
                        .map(MovieNightSegmentMapper::segmentToSegmentDto)
                        .collect(Collectors.toList()));
        // .orElse(MnmApiResponse.notFound());
    }
}