package com.example.restaurantservice.exception.not_found;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
