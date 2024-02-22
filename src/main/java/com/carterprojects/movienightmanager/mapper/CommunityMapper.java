package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.dto.CommunityDto;
import com.carterprojects.movienightmanager.model.dto.CommunitySummaryDto;
import com.carterprojects.movienightmanager.repository.models.Community;

import java.util.stream.Collectors;

public class CommunityMapper {
    public static CommunityDto communityToCommunityDto(Community community) {
        return CommunityDto.builder()
                .id(community.getId())
                .name(community.getCommunityName())
                .timezone(community.getTimezone())
                .userIds(community.getCommunityUsers().stream().map(user -> user.getUser().getId()).collect(Collectors.toList()))
                .createdOn(community.getCreatedOn())
                .createdByUsername(community.getCreatedByUsername())
                .build();
    }

    public static CommunitySummaryDto communityToCommunitySummaryDto(Community community) {
        return CommunitySummaryDto.builder()
                .id(community.getId())
                .name(community.getCommunityName())
                .build();
    }
}
