# Product Service

The Product module within the Restaurant Service is responsible for managing restaurant products, providing functionality for creating, retrieving, updating, and deleting products. The module is developed using Spring Boot and integrates with a database to store product information.

## API Documentation

### RequestMapping

`http://localhost:8081/api/v1/product`

## Endpoints

### 1. Get Products by Price Range

**Endpoint:** `GET /by-price`

**Description:** Retrieves a list of products within the specified price range.

**Request:**
```json
GET /by-price?minPrice=10.0&maxPrice=20.0
```
**Response:**

```json
[
  {
    "id": 1,
    "name": "Product 1",
    "description": "Description for Product 1",
    "price": 15.0,
    "category": {
      "categoryId": 1,
      "categoryName": "Category 1"
    },
    "image": {
      "imageUrl": "https://example.com/image1.jpg"
    }
  },
  // ... other products
]
```
### 2. Search Products by Name

**Endpoint:** `GET /search`

**Description:** Searches for products by name.

**Request:**
```GET /search?productName=Product```

```json
[
  {
    "id": 1,
    "name": "Product 1",
    "description": "Description for Product 1",
    "price": 15.0,
    "category": {
      "categoryId": 1,
      "categoryName": "Category 1"
    },
    "image": {
      "imageUrl": "https://example.com/image1.jpg"
    }
  },
  // ... other products
]
```

### 3. Get All Products

**Endpoint:** `GET /all`

**Description:** Retrieves a list of all products.

**Response:**

```json
[
  {
    "id": 1,
    "name": "Product 1",
    "description": "Description for Product 1",
    "price": 15.0,
    "category": {
      "categoryId": 1,
      "categoryName": "Category 1"
    },
    "image": {
      "imageUrl": "https://example.com/image1.jpg"
    }
  },
  // ... other products
]
```

### 4. Get Product by ID

**Endpoint:** `GET /{id}`

**Description:** Retrieves a product by its unique ID.

**Response:**
```json
{
  "id": 1,
  "name": "Product 1",
  "description": "Description for Product 1",
  "price": 15.0,
  "category": {
    "categoryId": 1,
    "categoryName": "Category 1"
  },
  "image": {
    "imageUrl": "https://example.com/image1.jpg"
  }
}

```

### 5. Add a New Product

**Endpoint:** `POST /create`

**Description:** Creates a new product.

**Request:**
```json
{
  "name": "New Product",
  "description": "Description for New Product",
  "price": 25.0,
  "category": {
    "categoryId": 2,
    "categoryName": "Category 2"
  },
  "image": {
    "imageUrl": "https://example.com/new_image.jpg"
  }
}
```

**Response:**
```json
{
  "id": 2,
  "name": "New Product",
  "description": "Description for New Product",
  "price": 25.0,
  "category": {
    "categoryId": 2,
    "categoryName": "Category 2"
  },
  "image": {
    "imageUrl": "https://example.com/new_image.jpg"
  }
}

```

### 6. Update a Product

**Endpoint:** `PUT /update/{productId}`

**Description:** Updates an existing product.

**Request:**
```json
{
  "name": "Updated Product",
  "description": "Updated description",
  "price": 30.0
}
```

**Response:**
```json
{
  "id": 2,
  "name": "Updated Product",
  "description": "Updated description",
  "price": 30.0,
  "category": {
    "categoryId": 2,
    "categoryName": "Category 2"
  },
  "image": {
    "imageUrl": "https://example.com/new_image.jpg"
  }
}

```

### 7. Delete a Product

**Endpoint:** `DELETE /delete/{productId}`


**Description:** Deletes a product by its ID.

**Response:**
```
No content
```