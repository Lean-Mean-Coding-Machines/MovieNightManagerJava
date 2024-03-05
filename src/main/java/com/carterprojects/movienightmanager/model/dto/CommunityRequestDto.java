package com.carterprojects.movienightmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityRequestDto {

    Integer userId;
    String communityName;
    String timezone;

}
