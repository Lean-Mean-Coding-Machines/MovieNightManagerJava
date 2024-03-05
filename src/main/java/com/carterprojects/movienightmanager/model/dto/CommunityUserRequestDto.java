package com.carterprojects.movienightmanager.model.dto;

import com.carterprojects.movienightmanager.repository.models.user.CommunityRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityUserRequestDto {

    Integer userId;
    Integer communityId;
    CommunityRole role;

}
