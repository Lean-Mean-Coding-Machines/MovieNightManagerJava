package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.AppUserDto;
import com.carterprojects.movienightmanager.model.AuthResponse;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;

public class AppUserMapper {
    public static AppUserDto appUserToDto(AppUser user) {
        return AppUserDto.builder()
                .id(user.getId())
                .userRole(user.getUserRole())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static AuthResponse appUserToAuthResponse(AppUser user, String token) {
        return AuthResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .token(token)
                .build();
    }
}
