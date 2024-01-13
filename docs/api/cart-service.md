# Cart Service

The Cart module within the Restaurant Service is responsible for managing user shopping carts, providing functionality for creating, retrieving, updating, and deleting carts. The module is developed using Spring Boot and integrates with a database to store cart information.

## API Documentation

### RequestMapping

`http://localhost:8081/api/v1/cart`

## Endpoints

### 1. Get Cart by ID

**Endpoint:** `GET /{cartId}`

**Description:** Retrieves a user's cart by its unique identifier.

**Response:**
```json
{
  "cartId": 1,
  "user": "john_doe",
  "cartItems": [
    {
      "cartItemId": 1,
      "product": {
        "productId": 101,
        "productName": "Product 1",
        "description": "Description for Product 1",
        "price": 15.0
      },
      "quantity": 2
    },
    // ... other cart items
  ]
}
```

### 2. Create New Cart

**Endpoint:** `POST /create`

**Description:** Creates a new cart for a user.

**Request Body:**
```json
{
  "user": "john_doe"
}
```

**Response:**
```json
{
  "cartId": 2,
  "user": "john_doe",
  "cartItems": []
}

```

### 3. Add Item to Cart

**Endpoint:** `POST /{cartId}/addItem/{cartItemId}`

**Description:** Adds an item to a user's cart.

**Request:**
```json
{
  "cartId": 1,
  "user": "john_doe",
  "cartItems": [
    {
      "cartItemId": 1,
      "product": {
        "productId": 101,
        "productName": "Product 1",
        "description": "Description for Product 1",
        "price": 15.0
      },
      "quantity": 4
    },
    // ... other cart items
  ]
}
```

**Response:**
```json
{
  "cartId": 1,
  "user": "john_doe",
  "cartItems": [
    {
      "cartItemId": 1,
      "product": {
        "productId": 101,
        "productName": "Product 1",
        "description": "Description for Product 1",
        "price": 15.0
      },
      "quantity": 4
    },
    // ... other cart items
  ]
}
```

### 4. Update Item Quantity in Cart

**Endpoint:** `PUT /{cartId}/updateItem/{cartItemId}`

**Description:** Updates the quantity of an item in a user's cart.

**Request:**
```json
{
  "cartId": 1,
  "user": "john_doe",
  "cartItems": [
    {
      "cartItemId": 1,
      "product": {
        "productId": 101,
        "productName": "Product 1",
        "description": "Description for Product 1",
        "price": 15.0
      },
      "quantity": 3
    },
    // ... other cart items
  ]
}
```

**Response:**
```json
{
  "cartId": 1,
  "user": "john_doe",
  "cartItems": [
    {
      "cartItemId": 1,
      "product": {
        "productId": 101,
        "productName": "Product 1",
        "description": "Description for Product 1",
        "price": 15.0
      },
      "quantity": 3
    },
    // ... other cart items
  ]
}
```

### 5. Remove Item from Cart

**Endpoint:** `DELETE /{cartId}/removeItem/{cartItemId}`

**Description:** Removes an item from a user's cart.

**Request:**
```json
{
  "cartId": 1,
  "user": "john_doe",
  "cartItems": [
    // ... remaining cart items
  ]
}
```

**Response:**
```json
{
  "cartId": 1,
  "user": "john_doe",
  "cartItems": [
    // ... remaining cart items
  ]
}   
```

### 6. Delete cart

**Endpoint:** `DELETE /{cartId}`

**Description:** Deletes a user's cart.

**Request:**
```
 http://localhost:8081/api/v1/cart/delete/2
```
        

**Response:**
``` 
No content
```
