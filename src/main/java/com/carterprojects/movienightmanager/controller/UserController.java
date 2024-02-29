package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.controller.security.JwtService;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.exception.ValidationException;
import com.carterprojects.movienightmanager.mapper.AppUserMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.user.UserCreateRequest;
import com.carterprojects.movienightmanager.model.user.UserCredentials;
import com.carterprojects.movienightmanager.service.UserService;
import com.carterprojects.movienightmanager.validators.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AccountExpiredException;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    UserService userServiceImpl;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    @PostMapping("authenticate")
    public ResponseEntity<MnmApiResponse> loginUser(@RequestBody UserCredentials creds) {
        try {
            if (creds.getUsername() == null || creds.getPassword() == null) {
                return MnmApiResponse.failed("Username or Password is empty");
            }
    
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword()
                    )
            );
    
            return userServiceImpl.getUserByCredentials(creds)
                    .map(user -> MnmApiResponse.success(AppUserMapper.appUserToAuthResponse(user, jwtService.generateToken(user))))
                    .orElse(MnmApiResponse.failed("Username or Password is invalid"));
    
        } catch (AuthenticationException ex) {
            // Handle different authentication exceptions
            if (ex instanceof BadCredentialsException) {
                return MnmApiResponse.failed("Invalid username or password");
            } else if (ex instanceof DisabledException) {
                return MnmApiResponse.failed("User account is disabled");
            } else if (ex instanceof LockedException) {
                return MnmApiResponse.failed("User account is locked");
            } else if (ex instanceof AccountExpiredException) {
                return MnmApiResponse.failed("User account has expired");
            } else {
                return MnmApiResponse.failed("Authentication failed");
            }
        }
    }
    


    @Authorize
    @PostMapping("token/refresh/{userId}")
    public ResponseEntity<MnmApiResponse> refreshUser(@PathVariable Integer userId) {
        return userServiceImpl.getUserById(userId)
                .map(user -> MnmApiResponse.success(AppUserMapper.appUserToAuthResponse(user, jwtService.generateToken(user))))
                .orElse(MnmApiResponse.failed("Username or Password is invalid"));
    }

    @Authorize
    @GetMapping("all")
    public ResponseEntity<MnmApiResponse> getUsers() {
        return MnmApiResponse.success(
                userServiceImpl.getAllUsers()
                        .stream()
                        .map(AppUserMapper::appUserToDto)
                        .collect(Collectors.toList())
        );
    }

    @Authorize
    @GetMapping("/details/{userId}")
    public ResponseEntity<MnmApiResponse> getUserDetails(@PathVariable Integer userId) {
        return userServiceImpl
                .getUserById(userId)
                .map(user -> MnmApiResponse.success(AppUserMapper.appUserDetailsToDto(user)))
                .orElse(MnmApiResponse.notFound());
    }

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MnmApiResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) throws MnmAppException, ValidationException {
        UserValidator.validateUserCreate(userCreateRequest);
        return MnmApiResponse.created(userServiceImpl.createUserFromRequest(userCreateRequest));
    }

    @Authorize
    @DeleteMapping("delete/{userId}")
    public ResponseEntity<MnmApiResponse> deleteUser(@PathVariable Integer userId) {
        try {
            userServiceImpl.deleteUserAccount(userId);
            return MnmApiResponse.success("Successfully deleted user account");
        } catch (MnmAppException ex) {
            return MnmApiResponse.failed(ex.getMessage());
        }
    }
}