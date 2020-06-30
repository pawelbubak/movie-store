package com.take.store.model.exception;

import org.springframework.http.HttpStatus;

public class AccountDisabledException extends ServiceException {
    public AccountDisabledException(String username) {
        super(HttpStatus.FORBIDDEN, String.format("User account [%s] is locked.", username));
    }
}
