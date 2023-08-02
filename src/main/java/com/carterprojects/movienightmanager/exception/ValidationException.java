package com.carterprojects.movienightmanager.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends Exception {

    public ValidationException(String message) {
        super(message);
        log.error(message);
    }

}
