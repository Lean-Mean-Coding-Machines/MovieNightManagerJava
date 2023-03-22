package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.constant.MovieNightSegmentConstants;
import com.carterprojects.movienightmanager.mapper.MovieNightSegmentMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.repository.MovieNightSegmentRepository;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import com.carterprojects.movienightmanager.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
public class MovieNightSegmentServiceImpl implements MovieNightSegmentService {
    @Autowired
    MovieNightSegmentRepository movieNightSegmentRepository;

    public Optional<MovieNightSegment> getCurrentMovieNightSegment() {
        return movieNightSegmentRepository.getMovieNightSegmentByDate(DateUtil.getDateTimeUtc());
    }

    public Optional<MovieNightSegment> getMovieNightSegmentById(Integer id) {
        return movieNightSegmentRepository.findById(id);
    }

    public List<MovieNightSegment> getPreviousMovieNightSegments(Integer currentID, Integer numSegments) {
        // Subtract numSegments from current segment to get id of first segment in list

        return movieNightSegmentRepository.getPreviousMovieNightSegments(currentID - numSegments, currentID);
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
