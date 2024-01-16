package com.carterprojects.movienightmanager.validators;

import com.carterprojects.movienightmanager.exception.ValidationException;
import com.carterprojects.movienightmanager.model.nomination.NominationRequest;

import java.util.ArrayList;

public class NominationValidator {

    public static void validateNominationCreate(NominationRequest request) throws ValidationException {
        var errorList = new ArrayList<String>();

        if (request.getUserId() == null) {
            errorList.add("User id is required");
        }

        if (request.getMovieTitle() == null || request.getMovieTitle().isEmpty()) {
            errorList.add("Movie title is required");
        }

        if (request.getWatchDate() == null || request.getWatchDate().isEmpty()) {
            errorList.add("Watch date is required");
        }

        if (request.getSegmentId() == null) {
            errorList.add("Segment Id is required");
        }

        if (errorList.size() > 0) {
            throw new ValidationException(errorList.size() + " errors found: " + String.join(", ", errorList));
        }
    }

}
