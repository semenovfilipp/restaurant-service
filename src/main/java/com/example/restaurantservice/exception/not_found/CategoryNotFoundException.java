package com.example.restaurantservice.exception.not_found;

public class CategoryNotFoundException extends NotFoundException{
    public CategoryNotFoundException(Long categoryId) {
        super("Category not found with id: " + categoryId);
    }
    public CategoryNotFoundException() {
        super("Category not found");
    }
}
