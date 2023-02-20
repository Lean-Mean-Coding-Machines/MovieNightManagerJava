package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.UserCreateRequest;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import com.carterprojects.movienightmanager.repository.UserRole;
import com.carterprojects.movienightmanager.repository.models.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AppUserRepository appUserRepository;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser createUserFromRequest(UserCreateRequest createRequest) throws MnmAppException {
        var existingUser = appUserRepository.findAppUserByEmailOrUsername(createRequest.getEmail(), createRequest.getUsername());
        if (existingUser.isPresent()) {
            if (existingUser.get().getEmail().equalsIgnoreCase(createRequest.getEmail())) {
                throw new MnmAppException("A user with this email already exists");
            } else {
                throw new MnmAppException("A user with this username already exists");
            }
        }

        var newUser =
                AppUser.builder()
                        .userRole(UserRole.USER)
                        .email(createRequest.getEmail())
                        .firstName(createRequest.getFirstName())
                        .lastName(createRequest.getLastName())
                        .username(createRequest.getUsername())
                        .password(passwordEncoder.encode(createRequest.getPassword()))
                        .build();

        return appUserRepository.save(newUser);
    }
}
