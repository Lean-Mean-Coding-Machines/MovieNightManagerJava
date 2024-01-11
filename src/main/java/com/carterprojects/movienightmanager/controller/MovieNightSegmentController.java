package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.exception.ValidationException;
import com.carterprojects.movienightmanager.mapper.MovieNightSegmentMapper;
import com.carterprojects.movienightmanager.mapper.NominationsMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.dto.MovieNightSegmentRequestDto;
import com.carterprojects.movienightmanager.service.MovieNightSegmentService;
import com.carterprojects.movienightmanager.validators.NominationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/segment")
public class MovieNightSegmentController {
    @Autowired
    MovieNightSegmentService movieNightSegmentServiceImpl;

    @GetMapping("current/{communityId}")
    public ResponseEntity<MnmApiResponse> getCurrentMovieNightSegment(@PathVariable Integer communityId) {
        return movieNightSegmentServiceImpl
                .getCurrentMovieNightSegmentForCommunity(communityId)
                .map(segment -> MnmApiResponse.success(MovieNightSegmentMapper.segmentToSegmentDto(segment)))
                .orElse(MnmApiResponse.notFound());
    }

    @GetMapping("previous/{currentId}")
    public ResponseEntity<MnmApiResponse> getPreviousMovieNightSegments(@PathVariable Integer currentId,
                                                        @RequestParam(name = "numSegments", defaultValue = "3") Integer numSegments) {
        return MnmApiResponse.success(
                movieNightSegmentServiceImpl.getPreviousMovieNightSegments(currentId, numSegments)
                        .stream()
                        .map(MovieNightSegmentMapper::segmentToSegmentDto)
                        .collect(Collectors.toList()));
    }

    @Authorize
    @PostMapping("create")
    public ResponseEntity<MnmApiResponse> createMovieNightSegment(@RequestBody MovieNightSegmentRequestDto segmentRequest) {
        try {
            NominationValidator.validateNominationCreate(segmentRequest.getNomination());
            var newSegment = movieNightSegmentServiceImpl.createNewMovieNightSegment(segmentRequest);
            return MnmApiResponse.created(MovieNightSegmentMapper.segmentToSegmentDto(newSegment));
        } catch (MnmAppException | ValidationException ex) {
            return MnmApiResponse.failed(ex.getMessage());
        }
    }
}