package com.carterprojects.movienightmanager.validators;

import com.carterprojects.movienightmanager.exception.ValidationException;
import com.carterprojects.movienightmanager.model.user.UserCreateRequest;

import java.util.ArrayList;

public class UserValidator {

    public static void validateUserCreate(UserCreateRequest request) throws ValidationException {
        var errorList = new ArrayList<String>();

        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            errorList.add("First name is required");
        }

        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            errorList.add("Last name is required");
        }

        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            errorList.add("Email is required");
        }

        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            errorList.add("Username is required");
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            errorList.add("Password is required");
        }

        if (errorList.size() > 0) {
            throw new ValidationException(errorList.size() + " errors found: " + String.join(", ", errorList));
        }
    }

}
