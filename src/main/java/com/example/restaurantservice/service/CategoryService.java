package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.CategoryDto;
import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.entity.Product;

import java.util.List;

public interface CategoryService {

    // Получение
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long categoryId);

    List<ProductDto> getProductsByCategoryId(Long categoryId);

    // Добавление, Обновление
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(Long categoryId, CategoryDto updateCategoryDto);
    // Удаление

    void deleteCategory(Long categoryId);
}
