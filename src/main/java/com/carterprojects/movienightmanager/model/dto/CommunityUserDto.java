package com.carterprojects.movienightmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityUserDto {

    Integer userId;
    String username;
    String role;
    String joinedOn;

}
