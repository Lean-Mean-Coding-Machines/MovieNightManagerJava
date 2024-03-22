package com.carterprojects.movienightmanager.validators;

import com.carterprojects.movienightmanager.exception.ValidationException;
import com.carterprojects.movienightmanager.model.feedback.FeedbackRequest;
import com.carterprojects.movienightmanager.repository.models.Feedback;

import java.util.ArrayList;

public class FeedbackValidator {
    public static void validateFeedback(FeedbackRequest request) throws ValidationException {
        var errorList = new ArrayList<String>();

        if (request.getUserId() == null) {
            errorList.add("User id is required");
        }

        if (errorList.size() > 0) {
            throw new ValidationException(errorList.size() + " errors found: " + String.join(", ", errorList));
        }
    }
}
