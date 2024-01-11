package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.user.UserCreateRequest;
import com.carterprojects.movienightmanager.model.user.UserCredentials;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    AppUser createUserFromRequest(UserCreateRequest createRequest) throws MnmAppException;

    List<AppUser> getAllUsers();

    Optional<AppUser> getUserByCredentials(UserCredentials creds);

    Optional<AppUser> getUserById(Integer userId);

    void deleteUserAccount(Integer userId) throws MnmAppException;
}
