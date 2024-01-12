package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    public ProductControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts() {
        List<ProductDto> expectedProducts = Arrays.asList(
                new ProductDto(),
                new ProductDto()
        );
        when(productService.getAllProducts()).thenReturn(expectedProducts);

        ResponseEntity<List<ProductDto>> response = productController.getAllProducts();

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProducts, response.getBody());
    }

    @Test
    void getProduct() {
        
        Long productId = 1L;
        ProductDto expectedProduct = new ProductDto();
        when(productService.getProductById(productId)).thenReturn(expectedProduct);

        
        ResponseEntity<ProductDto> response = productController.getProduct(productId);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedProduct, response.getBody());
    }

    @Test
    void createProduct() {
        
        ProductDto productToCreate = new ProductDto();
        when(productService.createProduct(productToCreate)).thenReturn(productToCreate);

        
        ResponseEntity<ProductDto> response = productController.createProduct(productToCreate);

        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productToCreate, response.getBody());
    }

    @Test
    void updateProduct() {
        
        Long productId = 1L;
        ProductDto updatedProduct = new ProductDto();
        when(productService.updateProduct(productId, updatedProduct)).thenReturn(updatedProduct);

        
        ResponseEntity<ProductDto> response = productController.updateProduct(productId, updatedProduct);

        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    void deleteProduct() {
        
        Long productId = 1L;

        
        productController.deleteProduct(productId);

        
        verify(productService, times(1)).deleteProduct(productId);
    }
}
