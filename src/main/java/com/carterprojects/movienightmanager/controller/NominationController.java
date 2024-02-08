package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.exception.ValidationException;
import com.carterprojects.movienightmanager.mapper.NominationsMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.nomination.NominationRequest;
import com.carterprojects.movienightmanager.service.NominationService;
import com.carterprojects.movienightmanager.validators.NominationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/nomination")
public class NominationController {
    @Autowired
    NominationService nominationServiceImpl;

    @GetMapping("segment/{segmentId}")
    public ResponseEntity<MnmApiResponse> getNominationsByCurrentMovieNightSegment(@PathVariable Integer segmentId) {
        return MnmApiResponse.success(
                nominationServiceImpl.getAllNominationsBySegmentId(segmentId)
                        .stream()
                        .map(NominationsMapper::nominationToNominationWithLikesDto)
                        .collect(Collectors.toList())
        );
    }

    @Authorize
    @GetMapping("user/{userId}")
    public ResponseEntity<MnmApiResponse> getNominationsByUserId(@PathVariable Integer userId) {
        return MnmApiResponse.success(
                nominationServiceImpl.getAllNominationsByUserId(userId)
                        .stream()
                        .map(NominationsMapper::nominationToNominationDto)
                        .collect(Collectors.toList())
        );
    }

    @Authorize
    @PostMapping("create")
    public ResponseEntity<MnmApiResponse> createNomination(@RequestBody NominationRequest nominationRequest) throws MnmAppException, ValidationException {
        NominationValidator.validateNominationCreate(nominationRequest);
        var newNomination = nominationServiceImpl.createNominationFromRequest(nominationRequest);
        if (newNomination == null) {
            return MnmApiResponse.failed("Couldn't create nomination. Check logs for details.");
        }
        return MnmApiResponse.created(NominationsMapper.nominationToNominationDto(newNomination));
    }

    @Authorize
    @DeleteMapping("delete/{nominationId}")
    public ResponseEntity<MnmApiResponse> deleteNomination(@PathVariable Integer nominationId,
                                                           @QueryParam("userId") Integer userId,
                                                           @QueryParam("communityId") Integer communityId) {
        try {
            nominationServiceImpl.deleteNomination(nominationId, userId, communityId);
            return MnmApiResponse.success("Successfully deleted nomination");
        } catch (MnmAppException ex) {
            return MnmApiResponse.failed(ex.getMessage());
        }
    }
}
