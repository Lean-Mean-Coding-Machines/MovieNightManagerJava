package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.dto.user.AppUserCreatedDto;
import com.carterprojects.movienightmanager.model.dto.user.AppUserDetailsDto;
import com.carterprojects.movienightmanager.model.dto.user.AppUserSummaryDto;
import com.carterprojects.movienightmanager.model.dto.CommunitySummaryDto;
import com.carterprojects.movienightmanager.model.user.AuthResponse;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;

import java.util.List;
import java.util.stream.Collectors;

public class AppUserMapper {
    public static AppUserSummaryDto appUserToDto(AppUser user) {
        return AppUserSummaryDto.builder()
                .id(user.getId())
                .userRole(user.getUserRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static AppUserCreatedDto newUserToCreatedDto(AppUser user) {
        return AppUserCreatedDto.builder().username(user.getUsername()).password(user.getPassword()).build();
    }

    public static AppUserDetailsDto appUserDetailsToDto(AppUser user) {
        var nominationIds = user.getNominations().stream().map(Nomination::getId).toList();
        return AppUserDetailsDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .nominations(
                        user.getNominations()
                                .stream()
                                .map(NominationsMapper::nominationToNominationWithLikesDto)
                                .collect(Collectors.toList())
                )
                .nominationLikes(
                        user.getNominationLikes()
                                .stream()
                                .filter(like -> !nominationIds.contains(like.getId()) && like.getEnabled())
                                .map(like -> NominationsMapper.nominationToNominationDto(like.getNomination()))
                                .collect(Collectors.toList())
                )
                .communities(appUserCommunitiesToSummary(user))
                .build();
    }

    public static List<CommunitySummaryDto> appUserCommunitiesToSummary(AppUser user) {
        return user.getCommunities()
                .stream()
                .map(communityUser -> CommunityMapper.communityToCommunitySummaryDto(communityUser.getCommunity()))
                .toList();
    }

    public static AuthResponse appUserToAuthResponse(AppUser user, String token) {
        return AuthResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .communities(appUserCommunitiesToSummary(user))
                .token(token)
                .build();
    }
}
