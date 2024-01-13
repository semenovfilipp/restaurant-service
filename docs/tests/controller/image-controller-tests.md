# Image Controller Tests

## Overview

This section covers the tests for the `ImageController` class, which is responsible for handling image-related API requests.

## Test Cases

### 1. Upload Image

- **Method:** POST
- **Endpoint:** `/api/v1/image/upload`
- **Description:** Uploads an image file to the server.
- **Expectations:**
    - Returns HTTP status 200 (OK) for a valid image file.
    - Returns HTTP status 400 (Bad Request) for an invalid file type.
    - Returns HTTP status 500 (Internal Server Error) if the image upload service fails.
    - Verifies that the `uploadImage` method in `ImageService` is called.

### 2. Download Image

- **Method:** GET
- **Endpoint:** `/api/v1/image/get/{fileName}`
- **Description:** Retrieves the content of an image file by its name.
- **Expectations:**
    - Returns HTTP status 200 (OK) for an existing image file.
    - Returns the image content with the appropriate content type.
    - Verifies that the `getImage` method in `ImageService` is called.

## Test Environment

The tests are written using JUnit 5 and Mockito. They ensure the proper functionality of image upload and retrieval endpoints in the `ImageController`. 

