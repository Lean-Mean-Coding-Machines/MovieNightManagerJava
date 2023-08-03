package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.mapper.MovieNightSegmentMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.service.MovieNightSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/segment")
public class MovieNightSegmentController {
    @Autowired
    MovieNightSegmentService movieNightSegmentServiceImpl;

    @GetMapping("current")
    public ResponseEntity<MnmApiResponse> getCurrentMovieNightSegment() {
        return movieNightSegmentServiceImpl
                .getCurrentMovieNightSegment()
                .map(segment -> MnmApiResponse.success(MovieNightSegmentMapper.segmentToSegmentDto(segment)))
                .orElse(MnmApiResponse.notFound());
    }

    @GetMapping("previous/{currentID}")
    public ResponseEntity<MnmApiResponse> getPreviousMovieNightSegments(@PathVariable Integer currentID,
                                                        @RequestParam(name = "numSegments", defaultValue = "3") Integer numSegments) {
        return MnmApiResponse.success(
                movieNightSegmentServiceImpl.getPreviousMovieNightSegments(currentID, numSegments)
                        .stream()
                        .map(MovieNightSegmentMapper::segmentToSegmentDto)
                        .collect(Collectors.toList()));
    }
}