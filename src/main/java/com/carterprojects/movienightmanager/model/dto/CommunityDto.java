package com.carterprojects.movienightmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityDto {

    Integer id;
    String name;
    String timezone;
    List<Integer> userIds;
    LocalDateTime createdOn;
    String createdByUsername;

}
