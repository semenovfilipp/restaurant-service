package com.example.restaurantservice.exception.not_found;

public class ProductsFromCategoryNotFoundException extends NotFoundException{
    public ProductsFromCategoryNotFoundException(Long categoryId) {
        super("Products not found for category with id: " + categoryId);
    }
}
