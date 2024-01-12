package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.ProductDto;

import java.util.List;

public interface ProductService {

    // Получение
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long productId);
    List<ProductDto> findProductsByPriceRange(Double minPrice, Double maxPrice);
    List<ProductDto> findProductsByName(String productName);


    // Создание, обновление
    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(Long productId, ProductDto productDto);

    // Удаление
    void deleteProduct(Long productId);
}
