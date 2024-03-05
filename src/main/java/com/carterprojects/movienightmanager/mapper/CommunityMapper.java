package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.dto.CommunityDto;
import com.carterprojects.movienightmanager.model.dto.CommunitySummaryDto;
import com.carterprojects.movienightmanager.model.dto.CommunityUserDto;
import com.carterprojects.movienightmanager.repository.models.Community;
import com.carterprojects.movienightmanager.repository.models.CommunityUser;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class CommunityMapper {

    static DateTimeFormatter joinedOnFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    static DateTimeFormatter createdOnFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public static CommunityDto communityToCommunityDto(Community community) {
        return CommunityDto.builder()
                .id(community.getId())
                .name(community.getCommunityName())
                .timezone(community.getTimezone())
                .userData(
                        community.getCommunityUsers().stream()
                                .filter(CommunityUser::isEnrolled)
                                .map(CommunityMapper::userToCommunityUserDto)
                                .collect(Collectors.toList())
                )
                .createdOn(community.getCreatedOn().format(createdOnFormatter))
                .createdByUsername(community.getCreatedBy().getUsername())
                .createdById(community.getCreatedBy().getId())
                .build();
    }

    public static CommunitySummaryDto communityToCommunitySummaryDto(Community community) {
        return CommunitySummaryDto.builder()
                .id(community.getId())
                .name(community.getCommunityName())
                .build();
    }

    public static CommunityUserDto userToCommunityUserDto(CommunityUser communityUser) {
        var user = communityUser.getUser();
        return CommunityUserDto.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .role(communityUser.getCommunityRole().name())
                .joinedOn(communityUser.getCreatedOn().format(joinedOnFormatter))
                .build();
    }
}
