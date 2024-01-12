package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.CategoryDto;
import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    // GET
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        log.info("Request to get all categories");
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
        log.info("Request to get category with id: {}", categoryId);
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable Long categoryId) {
        log.info("Request to get products by category id: {}", categoryId);
        return ResponseEntity.ok(categoryService.getProductsByCategoryId(categoryId));
    }

    // POST
    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto aCategoryDto) {
        log.info("Request to create a new category: {}", aCategoryDto);
        return ResponseEntity.ok(categoryService.createCategory(aCategoryDto));
    }

    // PUT
    @PutMapping("/update/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable Long categoryId,
            @RequestBody CategoryDto categoryDto
    ) {
        log.info("Request to update category with id: {}, new data: {}", categoryId, categoryDto);
        return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryDto));
    }

    // DELETE
    @DeleteMapping("/delete/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        log.info("Request to delete category with id: {}", categoryId);
        categoryService.deleteCategory(categoryId);
        log.info("Category with id: {} deleted successfully", categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
