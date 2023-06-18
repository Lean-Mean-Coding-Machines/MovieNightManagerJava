package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.JwtService;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.UserCreateRequest;
import com.carterprojects.movienightmanager.model.UserCredentials;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import com.carterprojects.movienightmanager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    UserService userServiceImpl;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
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
            return MnmApiResponse.failed("Could not authenticate user");
        }

        return userServiceImpl.getUserByCredentials(creds)
                .map(user -> MnmApiResponse.success(jwtService.generateToken(user)))
                .orElse(MnmApiResponse.failed("Username or Password is invalid"));
    }

    @GetMapping("all")
    public List<AppUser> getUsers() {
        return userServiceImpl.getAllUsers();
    }

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public MnmApiResponse createUser(@RequestBody UserCreateRequest userCreateRequest) throws MnmAppException {
        return MnmApiResponse.created(userServiceImpl.createUserFromRequest(userCreateRequest));
    }
}