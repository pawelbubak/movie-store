package com.take.store.model.exception;

import org.springframework.http.HttpStatus;

public class CartItemNotFoundException extends ServiceException {
    public CartItemNotFoundException(long movieId) {
        super(HttpStatus.NOT_FOUND, String.format("Cart item with movie id: %d not found", movieId));
    }
}
