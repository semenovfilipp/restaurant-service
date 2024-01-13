# Product Controller Tests

## Overview

This section covers the tests for the `ProductController` class, responsible for handling various product-related API endpoints.

## Test Cases

### 1. Get All Products

- **Method:** GET
- **Endpoint:** `/api/v1/products`
- **Description:** Retrieves a list of all products.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Verifies that the response contains the expected list of product DTOs.
    - Verifies that the `getAllProducts` method in `ProductService` is called.

### 2. Get Product by ID

- **Method:** GET
- **Endpoint:** `/api/v1/products/{productId}`
- **Description:** Retrieves details of a specific product by its ID.
- **Expectations:**
    - Returns HTTP status 200 (OK) for an existing product.
    - Verifies that the response contains the expected product DTO.
    - Verifies that the `getProductById` method in `ProductService` is called.

### 3. Create Product

- **Method:** POST
- **Endpoint:** `/api/v1/products`
- **Description:** Creates a new product.
- **Expectations:**
    - Returns HTTP status 201 (Created) for a valid product creation.
    - Verifies that the response contains the expected product DTO.
    - Verifies that the `createProduct` method in `ProductService` is called.

### 4. Update Product

- **Method:** PUT
- **Endpoint:** `/api/v1/products/{productId}`
- **Description:** Updates details of an existing product.
- **Expectations:**
    - Returns HTTP status 200 (OK) for a successful update.
    - Verifies that the response contains the expected updated product DTO.
    - Verifies that the `updateProduct` method in `ProductService` is called.

### 5. Delete Product

- **Method:** DELETE
- **Endpoint:** `/api/v1/products/{productId}`
- **Description:** Deletes an existing product.
- **Expectations:**
    - No specific response content; HTTP status 200 (OK) indicates successful deletion.
    - Verifies that the `deleteProduct` method in `ProductService` is called.

## Test Environment

The tests are written using JUnit 5 and Mockito, ensuring the proper functionality of product-related endpoints in the `ProductController`.

