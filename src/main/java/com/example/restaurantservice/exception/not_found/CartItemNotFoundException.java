package com.example.restaurantservice.exception.not_found;

public class CartItemNotFoundException extends NotFoundException {

    public CartItemNotFoundException(String message) {
        super(message);
    }
}
