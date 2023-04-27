package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.mapper.AppUserMapper;
import com.carterprojects.movienightmanager.model.AppUserDto;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.UserCreateRequest;
import com.carterprojects.movienightmanager.repository.models.AppUser;
import com.carterprojects.movienightmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userServiceImpl;

    @GetMapping("all")
    public MnmApiResponse getUsers() {
        return MnmApiResponse.success(
            userServiceImpl.getAllUsers()
                .stream()
                .map(AppUserMapper::appUserToDto)
                .collect(Collectors.toList())
        );
    }

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public MnmApiResponse createUser(@RequestBody UserCreateRequest userCreateRequest) throws MnmAppException {
        return MnmApiResponse.created(userServiceImpl.createUserFromRequest(userCreateRequest));
    }
}