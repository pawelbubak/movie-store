package com.take.store.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

class ServiceException extends ResponseStatusException {
    ServiceException(HttpStatus status, String reason) {
        super(status, reason);
    }
}
