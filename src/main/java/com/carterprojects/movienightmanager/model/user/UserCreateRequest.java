package com.carterprojects.movienightmanager.model.user;

import lombok.Getter;

import java.util.List;

@Getter
public class UserCreateRequest {
    String firstName;
    String lastName;
    String username;
    String password;
    String email;
    List<Integer> communityIds;
}
