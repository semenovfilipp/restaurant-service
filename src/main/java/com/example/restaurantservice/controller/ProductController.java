package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/by-price")
    public ResponseEntity<List<ProductDto>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice
    ) {
        log.info("Request to get products by price range: {} - {}", minPrice, maxPrice);
        List<ProductDto> products = productService.findProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(
            @RequestParam(required = false) String productName) {
        log.info("Request to search products by name: {}", productName);
        return ResponseEntity.ok(productService.findProductsByName(productName));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.info("Request to get all products");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        log.info("Request to get product with id: {}", productId);
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.info("Request to create product: {}", productDto);
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId,
                                                    @RequestBody ProductDto productDto) {
        log.info("Request to update product: {}", productDto);
        return ResponseEntity.ok(productService.updateProduct(productId, productDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        log.info("Request to delete product with id: {}", productId);
        productService.deleteProduct(productId);
        log.info("Successfully deleted product with id: {}", productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}

