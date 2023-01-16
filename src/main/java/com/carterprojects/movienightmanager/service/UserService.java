package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.UserCreateRequest;
import com.carterprojects.movienightmanager.repository.models.AppUser;

import java.util.List;

public interface UserService {
    AppUser createUserFromRequest(UserCreateRequest createRequest) throws MnmAppException;
    List<AppUser> getAllUsers();
}
