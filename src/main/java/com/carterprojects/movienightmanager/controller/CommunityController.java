package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.mapper.CommunityMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.dto.CommunityRequestDto;
import com.carterprojects.movienightmanager.model.dto.CommunityUserRequestDto;
import com.carterprojects.movienightmanager.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/community")
public class CommunityController {
    @Autowired
    CommunityService communityServiceImpl;

    @GetMapping("{communityId}")
    public ResponseEntity<MnmApiResponse> getCommunity(@PathVariable Integer communityId) {
        return communityServiceImpl
                .getCommunityById(communityId)
                .map(community -> MnmApiResponse.success(CommunityMapper.communityToCommunityDto(community)))
                .orElse(MnmApiResponse.notFound());
    }

    @GetMapping("/summary/user/{userId}")
    public ResponseEntity<MnmApiResponse> getCommunitySummariesByUser(@PathVariable Integer userId) {
        return MnmApiResponse.success(
                communityServiceImpl
                        .getCommunitiesByUserId(userId)
                        .stream()
                        .map(CommunityMapper::communityToCommunitySummaryDto)
                        .toList()
        );
    }

    @Authorize
    @PostMapping("create")
    public ResponseEntity<MnmApiResponse> createCommunity(@RequestBody CommunityRequestDto communityRequest) {
        try {
            var newCommunity = communityServiceImpl.createNewCommunity(communityRequest);
            return MnmApiResponse.created(CommunityMapper.communityToCommunityDto(newCommunity));
        } catch (MnmAppException ex) {
            return MnmApiResponse.failed(ex.getMessage());
        }
    }

    @Authorize
    @PostMapping("user/add")
    public ResponseEntity<MnmApiResponse> addCommunityUser(@RequestBody CommunityUserRequestDto communityUserRequest) {
        try {
            communityServiceImpl.createCommunityUser(
                    communityUserRequest.getCommunityId(),
                    communityUserRequest.getUserId(),
                    communityUserRequest.getRole()
            );
            return MnmApiResponse.created();
        } catch (MnmAppException ex) {
            return MnmApiResponse.failed(ex.getMessage());
        }
    }

    @Authorize
    @DeleteMapping("delete/{id}")
    public ResponseEntity<MnmApiResponse> deleteCommunity(@PathVariable("id") Integer id) {
        try {
            communityServiceImpl.deleteCommunity(id);
            return MnmApiResponse.success("Successfully deleted segment");
        } catch (MnmAppException ex) {
            return MnmApiResponse.failed(ex.getMessage());
        }
    }

}