package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.dto.CommunityRequestDto;
import com.carterprojects.movienightmanager.model.dto.MovieNightSegmentRequestDto;
import com.carterprojects.movienightmanager.repository.models.Community;
import com.carterprojects.movienightmanager.repository.models.CommunityUser;
import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import com.carterprojects.movienightmanager.repository.models.user.CommunityRole;

import java.util.List;
import java.util.Optional;

public interface CommunityService {

    Optional<Community> getCommunityById(Integer communityId);
    Community createNewCommunity(CommunityRequestDto communityRequest) throws MnmAppException;
    CommunityUser createCommunityUser(Integer communityId, Integer userId, CommunityRole role) throws MnmAppException;
    CommunityUser createCommunityUser(Community community, AppUser user, CommunityRole role) throws MnmAppException;

}
