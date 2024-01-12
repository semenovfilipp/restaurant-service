package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.entity.Category;
import com.example.restaurantservice.entity.Product;
import com.example.restaurantservice.exception.not_found.CategoryNotFoundException;
import com.example.restaurantservice.exception.not_found.ProductNotFoundException;
import com.example.restaurantservice.mapper.CategoryMapper;
import com.example.restaurantservice.mapper.ProductMapper;
import com.example.restaurantservice.repository.ProductRepository;
import com.example.restaurantservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;


    // GET
    @Override
    public List<ProductDto> getAllProducts() {
        try {
            log.info("Attempting to retrieve all products");
            List<Product> products = productRepository.findAll();

            log.info("Retrieved {} products", products.size());
            return productMapper.toDto(products);

        } catch (Exception e) {
            log.error("An error occurred while retrieving all products", e);
            throw new RuntimeException("Failed to retrieve all products", e);
        }
    }

    @Override
    public ProductDto getProductById(Long productId) {
        try {
            log.info("Attempting to retrieve product with id: {}", productId);
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> {
                        log.warn("Product with id {} not found", productId);
                        return new ProductNotFoundException(productId);
                    });

            log.info("Retrieved product with id: {}", productId);
            return productMapper.toDto(product);

        } catch (Exception e) {
            log.error("An error occurred while retrieving product with id: {}", productId, e);
            throw new RuntimeException("Failed to retrieve product with id: " + productId, e);
        }
    }

    @Override
    public List<ProductDto> findProductsByPriceRange(Double minPrice, Double maxPrice) {
        try {
            List<Product> products = productRepository.findProductsByPrice(minPrice, maxPrice);
            return productMapper.toDto(products);
        } catch (Exception e){
            log.error("Error occurred while finding products by price range", e);
            throw new RuntimeException("Error finding products by price range", e);
        }
    }

    @Override
    public List<ProductDto> findProductsByName(String productName) {
        try{
            log.info("Searching products by name '{}'", productName);
            List<Product> allProducts = productRepository.findAll();

            List<Product> foundProducts = allProducts.stream()
                    .filter(product -> productName == null || product.getName().toLowerCase().contains(productName.toLowerCase()))
                    .toList();
            log.info("Found {} products", foundProducts.size());
            return productMapper.toDto(foundProducts);
        } catch (Exception e) {
            log.error("An error occurred while searching products", e);
            throw new RuntimeException("Failed to search products", e);
        }
    }


    // POST
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        try {
            Category category = categoryMapper.toEntity(productDto.getCategoryDto());
            if (category == null) {
                throw new CategoryNotFoundException();
            }

            log.info("Check product not empty: {}", productDto);
            if (productDto == null){
                throw new RuntimeException("Product details can not be empty");
            }
            Product product = productMapper.toEntity(productDto);


            log.info("Save product {}", product);
            productRepository.save(product);

            return productMapper.toDto(product);

        } catch (Exception e) {
            log.error("Error while creating product", e);
            throw new RuntimeException("Failed to create product", e);
        }
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        try {
            log.info("Try to find product by id: {}" , productId);
            Product existingProduct = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException(productId));

            log.info("Try to get category: {}", productDto.getCategoryDto());
            Category category = categoryMapper.toEntity(productDto.getCategoryDto());
            if (category == null) {
                throw new CategoryNotFoundException();
            }


            log.info("Updating product with id: {}", productId);
            Product updateProduct = Product.builder()
                    .id(productDto.getId())
                    .name(productDto.getName() != null ? productDto.getName() : existingProduct.getName())
                    .description(productDto.getDescription() != null ? productDto.getDescription() : existingProduct.getDescription())
                    .price(productDto.getPrice() != null ? productDto.getPrice() : existingProduct.getPrice())
                    .category(category)
                    .image(productDto.getImage() != null ? productDto.getImage() : existingProduct.getImage())
                    .build();

            log.info("Saving updated product with id: {}", productId);
            productRepository.save(updateProduct);
            return productMapper.toDto(updateProduct);

        } catch (Exception e) {
            log.error("An error occurred while updating product with id: {}", productId, e);
            throw new RuntimeException("Failed to update product with id: " + productId, e);
        }
    }


    @Override
    public void deleteProduct(Long productId) {
        log.info("Attempting to delete product with id: {}", productId);
        try {
            if (productRepository.existsById(productId)) {
                productRepository.deleteById(productId);
                log.info("Product with id {} deleted successfully", productId);
            } else {
                log.warn("Product with id {} not found, deletion skipped", productId);
                throw new ProductNotFoundException(productId);
            }
        } catch (DataAccessException e) {
            log.error("An error occurred while deleting product with id: {}", productId, e);
            throw new ProductNotFoundException(productId);
        }
    }


}
