package com.carterprojects.movienightmanager.service.impl;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.dto.MovieNightSegmentRequestDto;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import com.carterprojects.movienightmanager.repository.CommunityRepository;
import com.carterprojects.movienightmanager.repository.MovieNightSegmentRepository;
import com.carterprojects.movienightmanager.repository.models.Community;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import com.carterprojects.movienightmanager.service.MovieNightSegmentService;
import com.carterprojects.movienightmanager.service.NominationService;
import com.carterprojects.movienightmanager.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MovieNightSegmentServiceImpl implements MovieNightSegmentService {
    @Autowired
    MovieNightSegmentRepository movieNightSegmentRepository;

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    NominationService nominationServiceImpl;

    public Optional<MovieNightSegment> getCurrentMovieNightSegmentForCommunity(Integer communityId) {
        return movieNightSegmentRepository.getMovieNightSegmentByDateAndCommunityId(DateUtil.getDateTimeUtc(), communityId);
    }

    public Optional<MovieNightSegment> getMovieNightSegmentById(Integer id) {
        return movieNightSegmentRepository.findById(id);
    }

    public List<MovieNightSegment> getPreviousMovieNightSegments(Integer currentID, Integer numSegments) {
        // Subtract numSegments from current segment to get id of first segment in list

        return movieNightSegmentRepository.getPreviousMovieNightSegments(currentID - numSegments, currentID);
    }

    public MovieNightSegment createNewMovieNightSegment(MovieNightSegmentRequestDto segmentRequest) throws MnmAppException {
        var community = communityRepository.findById(segmentRequest.getCommunityId())
                .orElseThrow(
                        () -> {
                            var errorStr = "No community found with id: " + segmentRequest.getCommunityId();
                            log.error(errorStr);
                            return new MnmAppException(errorStr);
                        }
                );

        if (hasSegmentCollision(community, segmentRequest.getNominationStartDate())) {
            throw new MnmAppException("Segment found with an overlapping time");
        }

        var user = appUserRepository.findById(segmentRequest.getUserId())
                .orElseThrow(
                        () -> {
                            log.error("Could not create nomination because user with id: {} was not found", segmentRequest.getUserId());
                            return new MnmAppException("Could create nomination because the user was not found");
                        }
                );

        var newSegment = movieNightSegmentRepository.save(
                MovieNightSegment.builder()
                        .nominationStartDate(segmentRequest.getNominationStartDate())
                        .nominationLockDate(segmentRequest.getNominationLockDate())
                        .segmentEndDate(segmentRequest.getChosenWatchDate())
                        .chosenWatchDate(segmentRequest.getChosenWatchDate())
                        .community(community)
                        .user(user)
                        .build()
        );

        segmentRequest.getNomination().setSegmentId(newSegment.getId());

        var newNomination = nominationServiceImpl.saveNewNomination(segmentRequest.getNomination(), newSegment, user);
        newSegment.setNominations(List.of(newNomination));

        return newSegment;
    }

    private boolean hasSegmentCollision(Community community, LocalDateTime proposedTime) {
        return community
                .getMovieNightSegments()
                .stream()
                .anyMatch(seg ->
                        seg.getNominationStartDate().isBefore(proposedTime) &&
                                seg.getSegmentEndDate().isAfter(proposedTime)
                );
    }

}
