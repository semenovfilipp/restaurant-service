## ImageServiceTests

### 1. Upload Image

#### Test Case: Valid File Successfully Uploaded
- **Input**:
    - MockMultipartFile with a valid image file
- **Expected Output**:
    - Successfully uploads the image and does not throw any exception.

#### Test Case: Empty File
- **Input**:
    - MockMultipartFile with an empty image file
- **Expected Output**:
    - Throws `RuntimeException` with the message "Failed to store empty file". The image repository save method should not be called.

### 2. Get Image

#### Test Case: Existing Image Name
- **Input**:
    - Image name: "test.jpg"
    - Existing Image in the repository with image data
- **Expected Output**:
    - Returns the image data for the specified image name.

#### Test Case: Non-Existing Image Name
- **Input**:
    - Image name: "nonexistent.jpg"
    - Image not found in the repository
- **Expected Output**:
    - Throws `ImageNotFoundException` with the message "Image not found". The image repository findByName method should be called.

### Note:
- The tests utilize Mockito for mocking dependencies.
- The `@Mock` annotation is used to create a mock of the `ImageRepository`.
- The `@InjectMocks` annotation is used to inject the mocks into the `ImageServiceImpl`.
- Each test method represents a specific scenario with defined input and expected output.
- The `verify` statements are used to ensure that specific methods are called on the mocked dependencies.

