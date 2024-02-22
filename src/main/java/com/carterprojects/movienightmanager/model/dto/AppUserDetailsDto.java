package com.carterprojects.movienightmanager.model.dto;


import com.carterprojects.movienightmanager.model.nomination.NominationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDetailsDto {
    Integer id;
    String firstName;
    String lastName;
    String username;
    String email;
    List<NominationDto> nominations;
    List<NominationDto> nominationLikes;
    List<CommunitySummaryDto> communities;
}
