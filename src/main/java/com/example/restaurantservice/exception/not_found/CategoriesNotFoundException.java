package com.example.restaurantservice.exception.not_found;

public class CategoriesNotFoundException extends NotFoundException {
    public CategoriesNotFoundException() {
        super("No categories found");
    }
}
