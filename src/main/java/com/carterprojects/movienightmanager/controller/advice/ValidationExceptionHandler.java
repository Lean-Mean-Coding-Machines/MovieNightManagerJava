package com.carterprojects.movienightmanager.controller.advice;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.exception.ValidationException;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ValidationException.class })
    protected ResponseEntity<MnmApiResponse> handleValidationException(Exception ex) {
        return MnmApiResponse.failed(ex.getMessage());
    }
}