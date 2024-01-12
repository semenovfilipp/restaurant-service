package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.CategoryDto;
import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.entity.Category;
import com.example.restaurantservice.entity.Image;
import com.example.restaurantservice.entity.Product;
import com.example.restaurantservice.exception.not_found.ProductNotFoundException;
import com.example.restaurantservice.mapper.CategoryMapper;
import com.example.restaurantservice.repository.ProductRepository;
import com.example.restaurantservice.service.impl.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTests {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private final Long productId = 1L;

    /*
    Test for getProductById
     */
    @Test
    public void testGetProductById_ReturnProductDto(){
        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));
        ProductDto productDto = productService.getProductById(productId);
        assertNotNull(productDto);
    }

    @Test
    public void testGetProductById_ReturnNotFound(){
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(productId));
    }

    @Test
    public void testGetProductById_ReturnException(){
        when(productRepository.findById(productId)).thenThrow(new RuntimeException("Simulated error"));
        assertThrows(RuntimeException.class, () -> productService.getProductById(productId));
    }

    /*
    Test for getAllProducts
     */
    @Test
    public void testGetAllProducts_ReturnListProducts() {
        List<Product> mockProducts = Arrays.asList(
                Product.builder().id(1L).name("Product1").description("Description1").price(10.0).category(new Category()).image(new Image()).build(),
                Product.builder().id(2L).name("Product2").description("Description2").price(15.0).category(new Category()).image(new Image()).build());
        when(productRepository.findAll()).thenReturn(mockProducts);

        List<ProductDto> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllProducts_ReturnException(){
        when(productRepository.findAll()).thenThrow(new RuntimeException("Simulated error"));
        assertThrows(RuntimeException.class, () -> productService.getAllProducts());
    }

    /*
    Test for createProduct
     */
    @Test
    public void testCreateProduct_ReturnProductDto() {
        ProductDto inputDto = new ProductDto();
        inputDto.setName("TestProduct");

        CategoryDto aCategoryDto = new CategoryDto();
        aCategoryDto.setProductDtos(Collections.emptyList());
        inputDto.setCategoryDto(aCategoryDto);

        inputDto.setImage(new Image());
        inputDto.setPrice(20.0);

        Product createdProduct = Product.builder()
                .id(productId)
                .name("TestProduct")
                .category(new Category())
                .image(new Image())
                .price(20.0)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(createdProduct);

        ProductDto resultDto = productService.createProduct(inputDto);

        assertNotNull(resultDto);
        assertEquals("TestProduct", resultDto.getName());

        verify(productRepository, times(1)).save(any(Product.class));
    }


    @Test(expected = RuntimeException.class)
    public void testCreateProduct_ReturnException(){
        ProductDto inputDto = new ProductDto();
        inputDto.setName("TestProduct");
        inputDto.setCategoryDto(new CategoryDto());
        inputDto.setImage(new Image());
        inputDto.setPrice(20.0);

        when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Simulated error"));
        productService.createProduct(inputDto);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void testUpdateProduct_Success() {
        CategoryDto aCategoryDto = new CategoryDto();
        aCategoryDto.setId(1L);
        aCategoryDto.setName("Some Category");

        ProductDto someProductDto = new ProductDto();
        someProductDto.setName("Some Product");
        someProductDto.setDescription("Product Description");
        someProductDto.setPrice(10.00);
        someProductDto.setImage(new Image());
        someProductDto.setCategoryDto(aCategoryDto);

        Category someCategoryModel = new Category();
        when(categoryMapper.toEntity((CategoryDto) any())).thenReturn(someCategoryModel);

        Product someProduct = new Product();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(someProduct));
        when(productRepository.save(any())).thenReturn(someProduct);

        ProductDto updatedProductDto = productService.updateProduct(1L, someProductDto);


        assertNotNull(updatedProductDto);
        assertEquals(someProductDto.getName(), updatedProductDto.getName());
    }

    /*
    Test for deleteProduct
     */
    @Test
    public void testDeleteProduct_Success() {

        when(productRepository.existsById(productId)).thenReturn(true);

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test(expected = ProductNotFoundException.class)
    public void testDeleteProduct_ExceptionOnDelete() {
        Long productId = 1L;

        when(productRepository.existsById(productId)).thenReturn(true);
        doThrow(new DataAccessException("Simulated delete error") {}).when(productRepository).deleteById(productId);

        productService.deleteProduct(productId);
    }
}
