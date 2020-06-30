package com.take.store.model.exception;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends ServiceException {
    public UsernameAlreadyExistsException(String username) {
        super(HttpStatus.CONFLICT, String.format("Account with username: %s already exists.", username));
    }
}
