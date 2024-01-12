package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.CategoryDto;
import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTests {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void getAllCategories() {
        List<CategoryDto> categories = Collections.singletonList(new CategoryDto());
        when(categoryService.getAllCategories()).thenReturn(categories);

        ResponseEntity<List<CategoryDto>> response = categoryController.getAllCategories();

        assertEquals(categories, response.getBody());
    }

    @Test
    void getCategoryById() {
        long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.getCategoryById(categoryId)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.getCategoryById(categoryId);

        assertEquals(categoryDto, response.getBody());
    }

    @Test
    void getProductsByCategoryId() {
        long categoryId = 1L;
        List<ProductDto> products = Collections.singletonList(new ProductDto());
        when(categoryService.getProductsByCategoryId(categoryId)).thenReturn(products);

        ResponseEntity<List<ProductDto>> response = categoryController.getProductsByCategoryId(categoryId);

        assertEquals(products, response.getBody());
    }

    @Test
    void createCategory() {
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.createCategory(categoryDto)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.createCategory(categoryDto);

        assertEquals(categoryDto, response.getBody());
    }

    @Test
    void updateCategory() {
        long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        when(categoryService.updateCategory(categoryId, categoryDto)).thenReturn(categoryDto);

        ResponseEntity<CategoryDto> response = categoryController.updateCategory(categoryId, categoryDto);

        assertEquals(categoryDto, response.getBody());
    }

    @Test
    void deleteCategory() {
        long categoryId = 1L;

        Mockito.doNothing().when(categoryService).deleteCategory(categoryId);

        categoryController.deleteCategory(categoryId);

        Mockito.verify(categoryService, Mockito.times(1)).deleteCategory(categoryId);
    }
}
