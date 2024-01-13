# Order Service


The Order Service is responsible for managing orders in the restaurant management system. It provides essential functionality for creating, retrieving, updating, and deleting orders. The service is developed using Spring Boot and utilizes various technologies and libraries.

## API Documentation

### RequestMapping 

`http://localhost:8081/api/v1/order`

## Endpoints

### 1. Create Order

**Endpoint:** `POST /create`

**Description:** Creates a new order based on the provided order details.

**Request:**
```json
{
  "customerId": 1,
  "items": [
    {
      "productId": 101,
      "quantity": 2
    },
    {
      "productId": 102,
      "quantity": 1
    }
  ]
}
```
**Response:**
```json
{
  "orderId": 1,
  "customerId": 1,
  "orderStatus": "PROCESSING",
  "orderItems": [
    {
      "productId": 101,
      "quantity": 2
    },
    {
      "productId": 102,
      "quantity": 1
    }
  ],
  "user": "john_doe"
}
```

### 2. Get All Orders

**Endpoint:** `GET /all`

**Description:** Retrieves a list of all orders in the system.

**Response:**
```json
[
  {
    "orderId": 1,
    "customerId": 1,
    "orderStatus": "PROCESSING",
    "orderItems": [
      {
        "productId": 101,
        "quantity": 2
      },
      {
        "productId": 102,
        "quantity": 1
      }
    ],
    "user": "john_doe"
  },
  {
    "orderId": 2,
    "customerId": 2,
    "orderStatus": "DELIVERED",
    "orderItems": [
      {
        "productId": 103,
        "quantity": 3
      }
    ],
    "user": "jane_smith"
  }
  // ... other orders
]
```
### 3. Get Order By ID

**Endpoint:** `GET /{orderId}`

**Description:** Retrieves an order by its unique identifier.

**Response:**
```json
{
"orderId": 1,
"customerId": 1,
"orderStatus": "PROCESSING",
"orderItems": [
{
"productId": 101,
"quantity": 2
},
{
"productId": 102,
"quantity": 1
}
],
"user": "john_doe"
}
```
### 4. Update Order

**Endpoint:** `PUT /update/{orderId}`

**Description:** Updates an existing order with new information.

**Request:**


```json
{
  "customerId": 1,
  "orderStatus": "PROCESSING",
  "orderItems": [
    {
      "productId": 101,
      "quantity": 3
    }
  ]
}
```

**Response:**
```json
{
  "orderId": 1,
  "customerId": 1,
  "orderStatus": "PROCESSING",
  "orderItems": [
    {
      "productId": 101,
      "quantity": 3
    }
  ],
  "user": "john_doe"
}
```

### 5. Update Order Status

**Endpoint:** `PUT /update-status/{orderId}`

**Description:** Updates the status of an existing order.

**Request:**

```json
{
  "newStatus": "DELIVERED"
}
```

**Response:**
```json
{
  "orderId": 1,
  "customerId": 1,
  "orderStatus": "DELIVERED",
  "orderItems": [
    {
      "productId": 101,
      "quantity": 3
    }
  ],
  "user": "john_doe"
}
```

## 5. Update Order Status

**Endpoint:** `DELETE /delete/{orderId}`

**Description:** Deletes a specific order based on the provided order ID.
