package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.AppUserDto;
import com.carterprojects.movienightmanager.repository.models.AppUser;

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
}
