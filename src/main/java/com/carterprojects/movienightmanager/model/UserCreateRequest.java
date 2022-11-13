package com.carterprojects.movienightmanager.model;

import lombok.Getter;

@Getter
public class UserCreateRequest {
    String firstName;
    String lastName;
    String username;
    String password;
    String email;
}
