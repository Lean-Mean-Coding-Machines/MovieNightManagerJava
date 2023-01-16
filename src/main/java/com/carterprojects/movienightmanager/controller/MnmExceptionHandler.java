package com.carterprojects.movienightmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.MnmApiResponse;

@ControllerAdvice
public class MnmExceptionHandler 
  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { MnmAppException.class })
    protected ResponseEntity<MnmApiResponse> handleMnmAppException(Exception ex) {
        return ResponseEntity.ok(MnmApiResponse.failed(ex.getMessage()));
    }
}