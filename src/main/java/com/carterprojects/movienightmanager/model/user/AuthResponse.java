package com.carterprojects.movienightmanager.model.user;

import com.carterprojects.movienightmanager.model.dto.CommunitySummaryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    String username;
    Integer userId;
    List<CommunitySummaryDto> communities;
}
