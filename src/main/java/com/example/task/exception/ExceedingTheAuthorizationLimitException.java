package com.example.task.exception;

import org.springframework.security.core.AuthenticationException;

public class ExceedingTheAuthorizationLimitException extends AuthenticationException {

    public ExceedingTheAuthorizationLimitException(String msg) {
        super(msg);
    }
}
