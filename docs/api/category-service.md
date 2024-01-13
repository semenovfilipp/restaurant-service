# Category Service

The Category module within the Restaurant Service is responsible for managing restaurant categories, providing functionality for creating, retrieving, updating, and deleting categories. The module is developed using Spring Boot and integrates with a database to store category information.

## API Documentation

### RequestMapping

`http://localhost:8081/api/v1/category`

## Endpoints

### 1. Get All Categories

**Endpoint:** `GET /all`

**Description:** Retrieves a list of all categories.

**Response:**
```json
[
  {
    "categoryId": 1,
    "categoryName": "Appetizers",
    "products": [
      {
        "productId": 1,
        "productName": "Spring Rolls",
        "description": "Delicious spring rolls with dipping sauce",
        "price": 8.99
      },
      // ... other products
    ]
  },
  // ... other categories
]
```

### 2. Get Category by ID

**Endpoint:** `GET /{categoryId}`

**Description:** Retrieves a category by its unique ID.

**Response:**
```json
{
  "categoryId": 1,
  "categoryName": "Appetizers",
  "products": [
    {
      "productId": 1,
      "productName": "Spring Rolls",
      "description": "Delicious spring rolls with dipping sauce",
      "price": 8.99
    },
    // ... other products
  ]
}
```

### 3. Get products by category ID

**Endpoint:** `GET /{categoryId}/products`

**Description:** Retrieves a list of products belonging to a specific category.

**Response:**
```json
[
  {
    "productId": 1,
    "productName": "Spring Rolls",
    "description": "Delicious spring rolls with dipping sauce",
    "price": 8.99
  },
  // ... other products
]
```

### 4. Add a new category


**Endpoint:** `POST /create`

**Description:** Creates a new category with the provided name.

**Request:**
```json
{
  "categoryName": "Desserts"
}
```
***Response:***
```json
{
  "categoryId": 2,
  "categoryName": "Desserts"
}
```

### 5. Update category

**Endpoint:** `PUT /update/{categoryId}`

**Description:** Updates the name of an existing category.

**Request:**
```json
{
  "categoryName": "Sweets",
  "products": [
    {
      "productId": 3,
      "productName": "Chocolate Cake",
      "description": "Rich chocolate cake with a smooth ganache",
      "price": 12.99
    }
  ]
}
```

**Response:**
```json
{
  "categoryId": 2,
  "categoryName": "Sweets",
  "products": [
    {
      "productId": 3,
      "productName": "Chocolate Cake",
      "description": "Rich chocolate cake with a smooth ganache",
      "price": 12.99
    }
  ]
}
```

### 6. Delete a category

**Endpoint:** `DELETE /delete/{categoryId}`

**Description:** Deletes a category and all its associated products

**Request:**
```DELETE /delete/2```

**Response:**
```json
No content
```
