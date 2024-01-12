package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.dto.CategoryDto;
import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.entity.Category;
import com.example.restaurantservice.entity.Product;
import com.example.restaurantservice.exception.not_found.CategoriesNotFoundException;
import com.example.restaurantservice.exception.not_found.CategoryNotFoundException;
import com.example.restaurantservice.mapper.CategoryMapper;
import com.example.restaurantservice.mapper.ProductMapper;
import com.example.restaurantservice.repository.CategoryRepository;
import com.example.restaurantservice.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;

    // GET

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        try {
            log.info("Attempting to retrieve category with id: {}", categoryId);
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> {
                        log.warn("Category with id {} not found", categoryId);
                        throw new CategoryNotFoundException(categoryId);
                    });

            return categoryMapper.toDto(category);
        } catch (Exception ex) {
            log.error("An error occurred while retrieving category with id: {}", categoryId, ex);
            throw new RuntimeException("Failed to retrieve category with id: " + categoryId, ex);
        }
    }


    @Override
    public List<ProductDto> getProductsByCategoryId(Long categoryId) {
        try {
            log.info("Attempting to retrieve products for category with id: {}", categoryId);
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> {
                        log.warn("Category with id {} not found", categoryId);
                        throw new CategoryNotFoundException(categoryId);
                    });

            log.info("Get products from category: {}" , category.getId());
            List<Product> products = category.getProducts();
            log.info("Retrieved {} products for category with id: {}", products.size(), categoryId);

            return productMapper.toDto(products);
        } catch (Exception e) {
            log.error("An error occurred while retrieving products for category with id: {}", categoryId, e);
            throw new RuntimeException("Failed to retrieve products for category with id: " + categoryId, e);
        }
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        try {
            log.info("Retrieving all categories");
            List<Category> categories = categoryRepository.findAll();

            if (categories.isEmpty()) {
                log.warn("No categories found");
                throw new CategoriesNotFoundException();
            }
            return categoryMapper.toDto(categories);
        } catch (DataAccessException e) {
            log.error("Error retrieving categories", e);
            throw new RuntimeException("Error retrieving categories", e);
        }
    }

    // POST
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        try {
            if (categoryDto == null) {
                throw new IllegalArgumentException("Category must not be null");
            }

            log.info("Creating category with name: {}", categoryDto.getName());
            Category category = categoryMapper.toEntity(categoryDto);
            categoryRepository.save(category);

            return categoryMapper.toDto(category);
        } catch (Exception e) {
            log.error("An unexpected error occurred while creating a category", e);
            throw new RuntimeException("Failed to create category", e);
        }
    }


    // PUT
    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category existCategory = categoryRepository.findById(categoryId).orElseThrow(()
                -> new CategoryNotFoundException(categoryId));

        if (categoryDto == null) {
            throw new IllegalArgumentException("Category name must not be null");
        }

        log.info("Updating category with id: {}", categoryId);
        Category updateCategory = categoryMapper.toEntity(categoryDto);
        if (updateCategory.getName() != null) {
            existCategory.setName(updateCategory.getName());
        }
        if (updateCategory.getProducts() != null) {
            existCategory.setProducts(updateCategory.getProducts());
        }

        log.info("Saving category with id: {}", categoryId);
        categoryRepository.save(existCategory);

        return categoryMapper.toDto(existCategory);
    }

    // DELETE
    @Override
    public void deleteCategory(java.lang.Long categoryId) {
        log.info("Deleting category with id: {}", categoryId);

        try {
            categoryRepository.deleteById(categoryId);
            log.info("Category with id {} deleted successfully", categoryId);
        } catch (DataAccessException e) {
            log.error("Error deleting category with id: {}", categoryId, e);
            throw new CategoryNotFoundException(categoryId);
        }
    }
}
