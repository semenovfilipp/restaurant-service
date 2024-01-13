# Order Controller Tests

## Overview

This section covers the tests for the `OrderController` class, which handles various order-related API endpoints.

## Test Cases

### 1. Get All Orders

- **Method:** GET
- **Endpoint:** `/api/v1/order/all`
- **Description:** Retrieves a list of all orders.
- **Expectations:**
    - Returns HTTP status 200 (OK).
    - Verifies that the response JSON does not include specific fields like `id` and `status`.
    - Verifies that the `getAllOrders` method in `OrderService` is called.

### 2. Get Order by ID

- **Method:** GET
- **Endpoint:** `/api/v1/order/{orderId}`
- **Description:** Retrieves details of a specific order by its ID.
- **Expectations:**
    - Returns HTTP status 200 (OK) for an existing order.
    - Verifies that the response JSON does not include specific fields like `id` and `status`.
    - Verifies that the `getOrderById` method in `OrderService` is called.

### 3. Create Order

- **Method:** POST
- **Endpoint:** `/api/v1/order/create`
- **Description:** Creates a new order.
- **Expectations:**
    - Returns HTTP status 200 (OK) for a valid order.
    - Verifies that the response JSON does not include specific fields like `id` and `status`.
    - Verifies that the `createOrder` method in `OrderService` is called.

### 4. Update Order Status

- **Method:** PUT
- **Endpoint:** `/api/v1/order/update-status/{orderId}`
- **Description:** Updates the status of an existing order.
- **Expectations:**
    - Returns HTTP status 200 (OK) for a successful update.
    - Verifies that the response JSON does not include specific fields like `id` and `status`.
    - Verifies that the `updateOrderStatus` method in `OrderService` is called.

### 5. Update Order

- **Method:** PUT
- **Endpoint:** `/api/v1/order/update/{orderId}`
- **Description:** Updates details of an existing order.
- **Expectations:**
    - Returns HTTP status 200 (OK) for a successful update.
    - Verifies that the response JSON does not include specific fields like `id` and `status`.
    - Verifies that the `updateOrder` method in `OrderService` is called.

### 6. Delete Order

- **Method:** DELETE
- **Endpoint:** `/api/v1/order/delete/{orderId}`
- **Description:** Deletes an existing order.
- **Expectations:**
    - Returns HTTP status 204 (No Content) for a successful deletion.
    - Verifies that the `deleteOrder` method in `OrderService` is called.

## Test Environment

The tests are written using JUnit 5 and Mockito. They ensure the proper functionality of order-related endpoints in the `OrderController`. 

