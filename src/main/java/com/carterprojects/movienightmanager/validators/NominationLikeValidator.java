package com.carterprojects.movienightmanager.validators;

import com.carterprojects.movienightmanager.exception.ValidationException;
import com.carterprojects.movienightmanager.model.nomination.NominationLikeRequest;

import java.util.ArrayList;

public class NominationLikeValidator {

    public static void validateNominationLike(NominationLikeRequest request) throws ValidationException {
        var errorList = new ArrayList<String>();

        if (request.getUserId() == null) {
            errorList.add("User id is required");
        }

        if (request.getNominationId() == null) {
            errorList.add("Nomination Id is required");
        }

        if (errorList.size() > 0) {
            throw new ValidationException(errorList.size() + " errors found: " + String.join(", ", errorList));
        }
    }

}
