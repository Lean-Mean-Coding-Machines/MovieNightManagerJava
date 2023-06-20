package com.carterprojects.movienightmanager.model;

import com.carterprojects.movienightmanager.repository.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    Integer id;
    UserRole userRole;
    String firstName;
    String lastName;
    String username;
    String email; 
}
