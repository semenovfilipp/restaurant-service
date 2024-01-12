package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.CategoryDto;
import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.exception.not_found.CategoryNotFoundException;
import com.example.restaurantservice.mapper.CategoryMapper;
import com.example.restaurantservice.entity.Category;
import com.example.restaurantservice.entity.Image;
import com.example.restaurantservice.entity.Product;
import com.example.restaurantservice.repository.CategoryRepository;
import com.example.restaurantservice.service.impl.CategoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    /*
    Test for getCategoryById
     */
    @Test
    public void testGetCategoryById_Success() {
        java.lang.Long categoryId = 1L;
        List<Product> products = new ArrayList<>();
        Category aCategory = new Category(categoryId, "TestCategory", products);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(aCategory));

        CategoryDto result = categoryService.getCategoryById(categoryId);

        assertNotNull(result);
        assertEquals(categoryId, result.getId());
        assertEquals("TestCategory", result.getName());
        assertTrue(result.getProductDtos().isEmpty()); // Assuming products are empty in this test case

        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test(expected = RuntimeException.class)
    public void testGetCategoryById_CategoryNotFound() {
        java.lang.Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        CategoryDto result = categoryService.getCategoryById(categoryId);
    }

    @Test(expected = RuntimeException.class)
    public void testGetCategoryById_ExceptionInRepository() {
        java.lang.Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenThrow(new RuntimeException("Simulated repository error"));


        CategoryDto result = categoryService.getCategoryById(categoryId);
    }
    /*
    Test for getProductsByCategoryId
     */

    @Test
    public void testGetProductsByCategoryId_Success() {
        java.lang.Long categoryId = 1L;
        List<Product> products = Arrays.asList(
                Product.builder().id(1L).name("Product1").description("Description1").price(10.00).category(new Category()).image(new Image()).build(),
                Product.builder().id(2L).name("Product2").description("Description2").price(15.00).category(new Category()).image(new Image()).build());

        Category aCategory = new Category(categoryId, "TestCategory", products);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(aCategory));

        List<ProductDto> result = categoryService.getProductsByCategoryId(categoryId);

        assertNotNull(result);
        assertEquals(2, result.size()); // Assuming two products in the list
        assertEquals("Product1", result.get(0).getName());
        assertEquals("Product2", result.get(1).getName());

        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test(expected = RuntimeException.class)
    public void testGetProductsByCategoryId_CategoryNotFound() {
        java.lang.Long categoryId = 1L;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        categoryService.getProductsByCategoryId(categoryId);
    }

    @Test
    public void testGetAllCategories_Success() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category1", Collections.emptyList()),
                new Category(2L, "Category2", Collections.emptyList())
        );

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDto> result = categoryService.getAllCategories();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Category1", result.get(0).getName());
        assertEquals("Category2", result.get(1).getName());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllCategories_EmptyList() {
        when(categoryRepository.findAll()).thenReturn(Collections.emptyList());

        List<CategoryDto> result = categoryService.getAllCategories();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test(expected = RuntimeException.class)
    public void testGetAllCategories_DataAccessException() {
        when(categoryRepository.findAll()).thenThrow(new DataAccessException("Simulated DataAccessException") {
        });

        categoryService.getAllCategories();

    }

    @Test
    public void testCreateCategory_Success() {
        CategoryDto aCategoryDto = new CategoryDto(1L, "CategoryName", Collections.emptyList());
        Category category = categoryMapper.toEntity(aCategoryDto);
        Category savedCategory = new Category(1L, "CategoryName", Collections.emptyList());

        when(categoryRepository.save(category)).thenReturn(savedCategory);

        CategoryDto result = categoryService.createCategory(aCategoryDto);

        assertNotNull(result);
        assertEquals(1L, result.getId().longValue());
        assertEquals("CategoryName", result.getName());

        verify(categoryRepository, times(1)).save(category);
    }

    @Test(expected = RuntimeException.class)
    public void testCreateCategory_NullCategoryDto() {
        CategoryDto aCategoryDto = null;

        categoryService.createCategory(aCategoryDto);

    }

    @Test(expected = RuntimeException.class)
    public void testCreateCategory_SaveException() {
        CategoryDto aCategoryDto = new CategoryDto(1L, "CategoryName", Collections.emptyList());
        Category category = categoryMapper.toEntity(aCategoryDto);

        when(categoryRepository.save(category)).thenThrow(new DataIntegrityViolationException("Simulated Save Exception") {
        });

        categoryService.createCategory(aCategoryDto);

    }

    /*
    Test for updateCategory
     */
    @Test
    public void testUpdateCategory_Success() {
        Long categoryId = 1L;
        CategoryDto aCategoryDto = new CategoryDto(categoryId, "UpdatedCategory", Collections.emptyList());
        Category existingCategory = new Category(categoryId, "OriginalCategory", Collections.emptyList());

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));

        CategoryDto result = categoryService.updateCategory(categoryId, aCategoryDto);

        assertNotNull(result);
        assertEquals(categoryId, result.getId());
        assertEquals("UpdatedCategory", result.getName());

        verify(categoryRepository, times(1)).findById(categoryId);

        existingCategory = new Category(categoryId, "UpdatedCategory", Collections.emptyList());
        when(categoryRepository.save(existingCategory)).thenReturn(existingCategory);

        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).save(existingCategory);
    }


    @Test(expected = CategoryNotFoundException.class)
    public void testUpdateCategory_CategoryNotFound() {
        java.lang.Long categoryId = 1L;
        CategoryDto aCategoryDto = new CategoryDto(categoryId, "UpdatedCategory", Collections.emptyList());

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        categoryService.updateCategory(categoryId, aCategoryDto);
    }

    @Test
    public void testDeleteCategory_Success() {
        Long categoryId = 1L;

        categoryService.deleteCategory(categoryId);


        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test(expected = CategoryNotFoundException.class)
    public void testDeleteCategory_CategoryNotFound() {
        Long categoryId = 1L;

        doThrow(CategoryNotFoundException.class).when(categoryRepository).deleteById(categoryId);

        categoryService.deleteCategory(categoryId);
    }
}
