#  Image Service

The Image module within the Restaurant Service is responsible for handling image upload and retrieval. This module allows users with appropriate roles to upload images and retrieve them based on the provided file name. It uses Spring Boot and supports the upload and retrieval of images in various formats.

## API Documentation

### RequestMapping

`http://localhost:8081/api/v1/image`

## Endpoints

### 1. Upload Image

**Endpoint:** `POST /upload`

**Description:** Uploads an image to the system. Only users with the 'ADMIN' role are allowed to perform this action.

**Request:**
- **Type:** `multipart/form-data`
- **Field Name:** `image`

**Response:**
- **Success (200 OK):** Image uploaded successfully
- **Failure (400 Bad Request):** Invalid file type. Please upload an image.
- **Failure (500 Internal Server Error):** Failed to upload image.

### 2. Download Image

**Endpoint:** `GET /get/{fileName}`

**Description:** Downloads an image from the system based on the provided file name. Only users with the 'USER' role are allowed to perform this action.

**Response:**
- **Success (200 OK):** Image data in the specified format (e.g., image/png).
- **Failure (404 Not Found):** Image not found.

## Service - ImageService

### 1. Upload Image

**Method:** `uploadImage(MultipartFile file)`

**Description:** Saves the provided image file to the system.

### 2. Download Image

**Method:** `byte[] getImage(String imageName)`

**Description:** Retrieves the image data based on the provided image name.

## Entity - Image

### Attributes:

- **name (String):** The original name of the image file.
- **type (String):** The content type of the image file.
- **imageData (byte[]):** The compressed binary data of the image.

## Repository - ImageRepository

### 1. Save Image

**Method:** `Image save(Image image)`

**Description:** Saves the image entity to the database.

### 2. Find Image by Name

**Method:** `Optional<Image> findByName(String name)`

**Description:** Retrieves an image entity from the database based on the provided name.

## Util - ImageUtils

### 1. Compress Image

**Method:** `byte[] compressImage(byte[] imageData)`

**Description:** Compresses the binary image data before saving it to the database.

### 2. Decompress Image

**Method:** `byte[] decompressedImage(byte[] compressedImageData)`

**Description:** Decompresses the binary image data before returning it to the user.

