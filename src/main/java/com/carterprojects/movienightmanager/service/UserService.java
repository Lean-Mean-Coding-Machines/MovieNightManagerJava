package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.model.UserCreateRequest;
import com.carterprojects.movienightmanager.repository.models.AppUser;

import java.util.List;

public interface UserService {
    AppUser createUserFromRequest(UserCreateRequest createRequest);
    List<AppUser> getAllUsers();
}
