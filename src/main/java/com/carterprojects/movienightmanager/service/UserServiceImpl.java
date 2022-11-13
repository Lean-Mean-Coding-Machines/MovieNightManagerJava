package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.model.UserCreateRequest;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import com.carterprojects.movienightmanager.repository.UserRole;
import com.carterprojects.movienightmanager.repository.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AppUserRepository appUserRepository;

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser createUserFromRequest(UserCreateRequest createRequest) {
        var existingUser = appUserRepository.findAppUserByEmailOrUsername(createRequest.getEmail(), createRequest.getUsername());
        if (existingUser.isPresent()) {
            if (existingUser.get().getEmail().equalsIgnoreCase(createRequest.getEmail())) {
                log.error("A user with this email already exists");
            } else {
                log.error("A user with this username already exists");
            }
            return null;
        }

        var newUser =
                AppUser.builder()
                        .userRole(UserRole.USER)
                        .email(createRequest.getEmail())
                        .firstName(createRequest.getFirstName())
                        .lastName(createRequest.getLastName())
                        .username(createRequest.getUsername())
                        .password(createRequest.getPassword())
                        .build();

        return appUserRepository.save(newUser);
    }
}
