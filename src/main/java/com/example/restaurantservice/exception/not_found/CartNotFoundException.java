package com.example.restaurantservice.exception.not_found;

public class CartNotFoundException extends NotFoundException {

    public CartNotFoundException(String message) {
        super(message);
    }
}
