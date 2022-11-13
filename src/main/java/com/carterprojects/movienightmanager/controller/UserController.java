package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.model.UserCreateRequest;
import com.carterprojects.movienightmanager.repository.models.AppUser;
import com.carterprojects.movienightmanager.service.NominationService;
import com.carterprojects.movienightmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public MnmApiResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        var newUser = userServiceImpl.createUserFromRequest(userCreateRequest);
        if (newUser == null) {
            return MnmApiResponse.failed("Couldn't create nomination. Check logs for details.");
        }
        return MnmApiResponse.created(newUser);
    }
}