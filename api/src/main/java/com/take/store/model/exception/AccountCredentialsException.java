package com.take.store.model.exception;

import org.springframework.http.HttpStatus;

public class AccountCredentialsException extends ServiceException {
    public AccountCredentialsException() {
        super(HttpStatus.UNAUTHORIZED, "Login failed. Bad account credentials.");
    }
}
