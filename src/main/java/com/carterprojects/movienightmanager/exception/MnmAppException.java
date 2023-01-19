package com.carterprojects.movienightmanager.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MnmAppException extends Exception {
    public MnmAppException() {
        super();
    }
    public MnmAppException(String message, Throwable cause) {
        super(message, cause);
    }
    public MnmAppException(String logMessage, String message, Throwable cause) {
        super(message, cause);
        log.error(logMessage, cause);
    }
    public MnmAppException(String message) {
        super(message);
        log.error(message);
    }
    public MnmAppException(Throwable cause) {
        super(cause);
    }
}