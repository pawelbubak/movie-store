package com.take.store.model.exception;

import org.springframework.http.HttpStatus;

public class MovieNotFoundException extends ServiceException {
    public MovieNotFoundException(long movieId) {
        super(HttpStatus.NOT_FOUND, String.format("Movie with id: %d not found", movieId));
    }
}
