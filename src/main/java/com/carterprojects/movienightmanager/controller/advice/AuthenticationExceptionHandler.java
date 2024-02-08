package com.carterprojects.movienightmanager.controller.advice;

import com.carterprojects.movienightmanager.model.MnmApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AuthenticationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BadCredentialsException.class})
    protected ResponseEntity<MnmApiResponse> handleBadCredentialsException(Exception ex) {
        return MnmApiResponse.failed("Invalid username or password", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {LockedException.class})
    protected ResponseEntity<MnmApiResponse> handleLockedException(Exception ex) {
        return MnmApiResponse.failed("Account is locked. Please contact support.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {DisabledException.class})
    protected ResponseEntity<MnmApiResponse> handleDisabledException(Exception ex) {
        return MnmApiResponse.failed("Account is disabled. Please contact support.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {CredentialsExpiredException.class})
    protected ResponseEntity<MnmApiResponse> handleCredentialsExpiredException(Exception ex) {
        return MnmApiResponse.failed("Credentials have expired. Please contact support.", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    protected ResponseEntity<MnmApiResponse> handleAuthenticationException(Exception ex) {
        return MnmApiResponse.failed("Could not authenticate user", HttpStatus.UNAUTHORIZED);
    }
}