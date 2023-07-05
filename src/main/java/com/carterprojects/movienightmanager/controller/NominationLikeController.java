package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.mapper.NominationsMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.NominationLikeRequest;
import com.carterprojects.movienightmanager.service.NominationLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/nominationlike")
public class NominationLikeController {
    @Autowired
    NominationLikeService nominationServiceImpl;

    @Authorize
    @GetMapping("user/{userId}")
    public MnmApiResponse getNominationLikesByUserId(@PathVariable Integer userId) {
        return MnmApiResponse.success(
                nominationServiceImpl.getAllNominationLikesByUserId(userId)
                        .stream()
                        .map(NominationsMapper::nominationLikeToNominationLikeDto)
                        .collect(Collectors.toList())
        );
    }

    @Authorize
    @GetMapping("nomination/{nominationId}")
    public MnmApiResponse getNominationLikesByNominationId(@PathVariable Integer nominationId) {
        return MnmApiResponse.success(
                nominationServiceImpl.getAllNominationLikesByNominationId(nominationId)
                        .stream()
                        .map(NominationsMapper::nominationLikeToNominationLikeDto)
                        .collect(Collectors.toList())
        );
    }

    @Authorize
    @PostMapping("manage")
    public MnmApiResponse manageNominationLikeByRequest(@RequestBody NominationLikeRequest likeRequest) throws MnmAppException {
        if (likeRequest.getUserId() == null) {
            return MnmApiResponse.failed("userId is required.");
        }
        if (likeRequest.getNominationId() == null) {
            return MnmApiResponse.failed("nominationId is required.");
        }
        var newNominationLike = nominationServiceImpl.manageNominationLikeFromRequest(likeRequest);
        if (newNominationLike == null) {
            return MnmApiResponse.failed("Couldn't create nomination like. Check logs for details.");
        }
        return MnmApiResponse.created(NominationsMapper.nominationLikeToNominationLikeDto(newNominationLike));
    }
}
