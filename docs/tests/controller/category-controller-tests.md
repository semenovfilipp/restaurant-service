# Category Controller Tests

## Overview

This section covers the tests for the `CategoryController` class, responsible for handling API requests related to product categories.

## Test Cases

### 1. Get All Categories

- **Method:** GET
- **Endpoint:** `/api/v1/categories`
- **Description:** Retrieves all product categories.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Response body includes a list of `CategoryDto` objects.

### 2. Get Category by ID

- **Method:** GET
- **Endpoint:** `/api/v1/categories/{categoryId}`
- **Description:** Retrieves a specific product category by its ID.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Response body includes a single `CategoryDto` object.

### 3. Get Products by Category ID

- **Method:** GET
- **Endpoint:** `/api/v1/categories/{categoryId}/products`
- **Description:** Retrieves all products associated with a specific category.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Response body includes a list of `ProductDto` objects.

### 4. Create Category

- **Method:** POST
- **Endpoint:** `/api/v1/categories`
- **Description:** Creates a new product category.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Response body includes the created `CategoryDto` object.

### 5. Update Category

- **Method:** PUT
- **Endpoint:** `/api/v1/categories/{categoryId}`
- **Description:** Updates an existing product category.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Response body includes the updated `CategoryDto` object.

### 6. Delete Category

- **Method:** DELETE
- **Endpoint:** `/api/v1/categories/{categoryId}`
- **Description:** Deletes an existing product category.
- **Expectations:**
    - Returns HTTP status 204 (No Content).

## Test Environment

The tests are written using JUnit 5 and Mockito. They cover various scenarios to ensure the proper functioning of the `CategoryController` endpoints.

