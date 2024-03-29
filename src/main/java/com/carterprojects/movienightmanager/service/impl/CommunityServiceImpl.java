package com.carterprojects.movienightmanager.service.impl;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.dto.CommunityRequestDto;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import com.carterprojects.movienightmanager.repository.CommunityRepository;
import com.carterprojects.movienightmanager.repository.CommunityUserRepository;
import com.carterprojects.movienightmanager.repository.models.Community;
import com.carterprojects.movienightmanager.repository.models.CommunityUser;
import com.carterprojects.movienightmanager.repository.models.CommunityUserKey;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import com.carterprojects.movienightmanager.repository.models.user.CommunityRole;
import com.carterprojects.movienightmanager.service.CommunityService;
import com.carterprojects.movienightmanager.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    @Autowired
    CommunityUserRepository communityUserRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public Optional<Community> getCommunityById(Integer communityId) {
        return communityRepository.findById(communityId);
    }

    @Override
    public List<Community> getAllCommunities() {
        var communities = new ArrayList<Community>();
        communityRepository.findAll().forEach(communities::add);
        return communities;
    }

    public List<Community> getCommunitiesByUserId(Integer userId) {
        return communityUserRepository.findCommunityUserByUser_Id(userId).stream().map(CommunityUser::getCommunity).toList();
    }

    public Community createNewCommunity(CommunityRequestDto communityRequest) throws MnmAppException {
        var user = appUserRepository.findById(communityRequest.getUserId())
                .orElseThrow(
                        () -> {
                            log.error("Could not create community because user with id: {} was not found", communityRequest.getUserId());
                            return new MnmAppException("Could create community because the user was not found");
                        }
                );

        Community newCommunity;
        try {
            newCommunity = communityRepository.save(
                    Community.builder()
                            .communityName(communityRequest.getCommunityName())
                            .timezone(communityRequest.getTimezone())
                            .createdOn(DateUtil.getDateTimeUtc())
                            .modifiedOn(DateUtil.getDateTimeUtc())
                            .active(true)
                            .createdBy(user)
                            .build()
            );
        } catch (Exception ex) {
            log.error("Could not save community", ex);
            throw new MnmAppException("Could not save community. Try again soon.");
        }

        CommunityUser newCommunityUser = createCommunityUser(newCommunity, user, CommunityRole.CREATOR);

        newCommunity.setCommunityUsers(List.of(newCommunityUser));

        return newCommunity;
    }

    public CommunityUser createCommunityUser(Integer communityId, Integer userId, CommunityRole role) throws MnmAppException {
        var user = appUserRepository.findById(userId)
                .orElseThrow(
                        () -> {
                            log.error("Could not create community user because user with id: {} was not found", userId);
                            return new MnmAppException("Could create community because the user was not found");
                        }
                );

        var community = communityRepository.findById(communityId)
                .orElseThrow(
                        () -> {
                            log.error("No community found with id: " + communityId);
                            return new MnmAppException("No community found");
                        }
                );

        return createCommunityUser(community, user, role);
    }

    public CommunityUser createCommunityUser(Integer communityId, AppUser user, CommunityRole role) throws MnmAppException {
        var community = communityRepository.findById(communityId)
                .orElseThrow(
                        () -> {
                            log.error("No community found with id: " + communityId);
                            return new MnmAppException("No community found");
                        }
                );

        return createCommunityUser(community, user, role);
    }

    public CommunityUser createCommunityUser(Community community, AppUser user, CommunityRole role) throws MnmAppException {
        try {
            return communityUserRepository.save(
                    CommunityUser.builder()
                            .id(
                                    CommunityUserKey.builder()
                                            .communityId(community.getId())
                                            .userId(user.getId())
                                            .build()
                            )
                            .community(community)
                            .user(user)
                            .communityRole(role)
                            .enrolled(true)
                            .createdOn(LocalDateTime.now())
                            .modifiedOn(LocalDateTime.now())
                            .build()
            );
        } catch (Exception ex) {
            log.error("Could not save community user", ex);
            throw new MnmAppException("Could not save community user. Try again soon.");
        }
    }

    public void deleteCommunity(Integer id) throws MnmAppException {
        var community = communityRepository.findById(id)
                .orElseThrow(() -> {
                    var errorStr = "No current community found";
                    log.error(errorStr);
                    return new MnmAppException(errorStr);
                });
    
        try {
            communityRepository.delete(community);
        } catch (Exception ex) {
            log.error("Could not delete community", ex);
            throw new MnmAppException("Error while deleting community, please try again later");
        }
    }

}
