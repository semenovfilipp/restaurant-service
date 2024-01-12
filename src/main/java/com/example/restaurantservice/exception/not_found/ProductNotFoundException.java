package com.example.restaurantservice.exception.not_found;

public class ProductNotFoundException extends NotFoundException{
    public ProductNotFoundException(Long productId) {
        super("Product not found with id: " + productId);
    }
}
