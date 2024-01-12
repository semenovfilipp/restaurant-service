package com.example.restaurantservice.exception.already_exist;

import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.entity.Product;

public class ProductAlreadyExistsException extends AlreadyExistException{

    public ProductAlreadyExistsException(Product product) {
        super("Product " + product.getName() + " already exists");
    }

    public ProductAlreadyExistsException(ProductDto productDto) {
        super("Product " + productDto.getName() + " already exists");
    }
}
