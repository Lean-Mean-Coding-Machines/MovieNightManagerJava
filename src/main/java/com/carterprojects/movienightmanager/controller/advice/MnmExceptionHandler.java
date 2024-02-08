package com.carterprojects.movienightmanager.controller.advice;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MnmExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {MnmAppException.class})
    protected ResponseEntity<MnmApiResponse> handleMnmAppException(Exception ex) {
        return MnmApiResponse.failed(ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<MnmApiResponse> handleGenericException(Exception ex) {
        return MnmApiResponse.failed(ex.getMessage());
    }
}