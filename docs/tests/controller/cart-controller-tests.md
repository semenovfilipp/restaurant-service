# Cart Controller Tests

## Overview

This section covers the tests for the `CartController` class, responsible for handling API requests related to the shopping cart functionality.

## Test Cases

### 1. Get Cart by ID

#### Positive Scenario
- **Endpoint:** GET `/api/v1/cart/{cartId}`
- **Description:** Retrieves a cart by its ID.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Response body includes the `CartDto` with user and cart items omitted.

#### Negative Scenario
- **Endpoint:** GET `/api/v1/cart/{cartId}`
- **Description:** Attempts to retrieve a non-existing cart.
- **Expectations:**
    - Returns HTTP status 404 (Not Found).

### 2. Create Cart

- **Endpoint:** POST `/api/v1/cart/create`
- **Description:** Creates a new cart.
- **Expectations:**
    - Returns HTTP status 201 (Created).
    - Response body includes the `CartDto` with user and cart items omitted.

### 3. Add Item to Cart

- **Endpoint:** POST `/api/v1/cart/{cartId}/addItem/{cartItemId}`
- **Description:** Adds an item to an existing cart.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Response body includes the updated `CartDto` with user and cart items omitted.

### 4. Update Item Quantity in Cart

- **Endpoint:** PUT `/api/v1/cart/{cartId}/updateItem/{cartItemId}`
- **Description:** Updates the quantity of an item in the cart.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Response body includes the updated `CartDto` with user and cart items omitted.

### 5. Remove Item from Cart

- **Endpoint:** DELETE `/api/v1/cart/{cartId}/removeItem/{cartItemId}`
- **Description:** Removes an item from the cart.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Response body includes the updated `CartDto` with user and cart items omitted.

### 6. Delete Cart

#### Positive Scenario
- **Endpoint:** DELETE `/api/v1/cart/delete/{cartId}`
- **Description:** Deletes an existing cart.
- **Expectations:**
    - Returns HTTP status 204 (No Content).

#### Negative Scenario
- **Endpoint:** DELETE `/api/v1/cart/delete/{cartId}`
- **Description:** Attempts to delete a non-existing cart.
- **Expectations:**
    - Returns HTTP status 404 (Not Found).

## Test Environment

The tests are written using the Spring MVC Test framework and Mockito for mocking dependencies. The test cases cover various scenarios to ensure the proper functioning of the `CartController` endpoints.

