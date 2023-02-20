package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.UserCreateRequest;
import com.carterprojects.movienightmanager.repository.models.AppUser;
import com.carterprojects.movienightmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userServiceImpl;

    @GetMapping("all")
    public List<AppUser> getUsers() {
        return userServiceImpl.getAllUsers();
    }

    @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
    public MnmApiResponse createUser(@RequestBody UserCreateRequest userCreateRequest) throws MnmAppException {
        return MnmApiResponse.created(userServiceImpl.createUserFromRequest(userCreateRequest));
    }
}