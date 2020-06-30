package com.take.store.model.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyUsedException extends ServiceException {
    public EmailAlreadyUsedException(String email) {
        super(HttpStatus.CONFLICT, String.format("An account with an email address: %s already exists", email));
    }
}
