package com.carterprojects.movienightmanager.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppUserException extends Exception {
    public AppUserException() {
        super();
    }
    public AppUserException(String message, Throwable cause) {
        super(message, cause);
    }
    public AppUserException(String logMessage, String message, Throwable cause) {
        super(message, cause);
        log.error(logMessage, cause);
    }
    public AppUserException(String message) {
        super(message);
        log.error(message);
    }
    public AppUserException(Throwable cause) {
        super(cause);
    }
}