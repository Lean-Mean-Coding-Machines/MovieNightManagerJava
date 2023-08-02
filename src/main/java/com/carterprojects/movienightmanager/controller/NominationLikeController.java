package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.exception.ValidationException;
import com.carterprojects.movienightmanager.mapper.NominationsMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.nomination.NominationLikeRequest;
import com.carterprojects.movienightmanager.service.NominationLikeService;
import com.carterprojects.movienightmanager.validators.NominationLikeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/nominationlike")
public class NominationLikeController {
    @Autowired
    NominationLikeService nominationServiceImpl;

    @Authorize
    @GetMapping("user/{userId}")
    public ResponseEntity<MnmApiResponse> getNominationLikesByUserId(@PathVariable Integer userId) {
        return MnmApiResponse.success(
                nominationServiceImpl.getAllNominationLikesByUserId(userId)
                        .stream()
                        .map(NominationsMapper::nominationLikeToNominationLikeDto)
                        .collect(Collectors.toList())
        );
    }

    @Authorize
    @GetMapping("nomination/{nominationId}")
    public ResponseEntity<MnmApiResponse> getNominationLikesByNominationId(@PathVariable Integer nominationId) {
        return MnmApiResponse.success(
                nominationServiceImpl.getAllNominationLikesByNominationId(nominationId)
                        .stream()
                        .map(NominationsMapper::nominationLikeToNominationLikeDto)
                        .collect(Collectors.toList())
        );
    }

    @Authorize
    @PostMapping("manage")
    public ResponseEntity<MnmApiResponse> manageNominationLikeByRequest(@RequestBody NominationLikeRequest likeRequest) throws MnmAppException, ValidationException {
        NominationLikeValidator.validateNominationLike(likeRequest);
        var newNominationLike = nominationServiceImpl.manageNominationLikeFromRequest(likeRequest);
        if (newNominationLike == null) {
            return MnmApiResponse.failed("Couldn't create nomination like. Check logs for details.");
        }
        return MnmApiResponse.created(NominationsMapper.nominationLikeToNominationLikeDto(newNominationLike));
    }
}
