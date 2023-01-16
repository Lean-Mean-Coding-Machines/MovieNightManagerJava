package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.mapper.NominationsMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.NominationRequest;
import com.carterprojects.movienightmanager.service.NominationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/nomination")
public class NominationController {
    @Autowired
    NominationService nominationServiceImpl;

    @GetMapping("current")
    public MnmApiResponse getNominationsByCurrentMovieNightSegment() {
        return MnmApiResponse.success(
                nominationServiceImpl.getAllNominationsByCurrentSegment()
                        .stream()
                        .map(NominationsMapper::nominationToNominationWithLikesDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("user/{userId}")
    public MnmApiResponse getNominationsByUserId(@PathVariable Integer userId) {
        return MnmApiResponse.success(
                nominationServiceImpl.getAllNominationsByUserId(userId)
                        .stream()
                        .map(NominationsMapper::nominationToNominationDto)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("create")
    public MnmApiResponse createNomination(@RequestBody NominationRequest nominationRequest) throws MnmAppException {
        var newNomination = nominationServiceImpl.createNominationFromRequest(nominationRequest);
        if (newNomination == null) {
            return MnmApiResponse.failed("Couldn't create nomination. Check logs for details.");
        }
        return MnmApiResponse.created(NominationsMapper.nominationToNominationDto(newNomination));
    }
}
