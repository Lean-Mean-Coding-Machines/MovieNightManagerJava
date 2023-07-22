package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.controller.security.JwtService;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.mapper.AppUserMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.UserCreateRequest;
import com.carterprojects.movienightmanager.model.UserCredentials;
import com.carterprojects.movienightmanager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    UserService userServiceImpl;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    @PostMapping("authenticate")
    public MnmApiResponse loginUser(@RequestBody UserCredentials creds) {
        if (creds.getUsername() == null || creds.getPassword() == null) {
            return MnmApiResponse.failed("Username or Password is empty");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword()
                    )
            );
        } catch (AuthenticationException ex) {
            return MnmApiResponse.failed("Could not authenticate user", HttpStatus.UNAUTHORIZED);
        }

        return userServiceImpl.getUserByCredentials(creds)
                .map(user -> MnmApiResponse.success(AppUserMapper.appUserToAuthResponse(user, jwtService.generateToken(user))))
                .orElse(MnmApiResponse.failed("Username or Password is invalid"));
    }

    @Authorize
    @PostMapping("token/refresh/{userId}")
    public MnmApiResponse refreshUser(@PathVariable Integer userId) {
        return userServiceImpl.getUserById(userId)
                .map(user -> MnmApiResponse.success(AppUserMapper.appUserToAuthResponse(user, jwtService.generateToken(user))))
                .orElse(MnmApiResponse.failed("Username or Password is invalid"));
    }

    @Authorize
    @GetMapping("all")
    public MnmApiResponse getUsers() {
        return MnmApiResponse.success(
                userServiceImpl.getAllUsers()
                        .stream()
                        .map(AppUserMapper::appUserToDto)
                        .collect(Collectors.toList())
        );
    }

    @Authorize
    @GetMapping("/details/{userId}")
    public MnmApiResponse getUserDetails(@PathVariable Integer userId) {
        return userServiceImpl
                .getUserById(userId)
                .map(user -> MnmApiResponse.success(AppUserMapper.appUserDetailsToDto(user)))
                .orElse(MnmApiResponse.notFound());
    }

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public MnmApiResponse createUser(@RequestBody UserCreateRequest userCreateRequest) throws MnmAppException {
        return MnmApiResponse.created(userServiceImpl.createUserFromRequest(userCreateRequest));
    }
}