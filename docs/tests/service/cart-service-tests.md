## CartServiceTests

### 1. Get Cart by ID

#### Test Case: Existing Cart ID
- **Input**:
    - Cart ID: 1
- **Expected Output**:
    - Returns CartDto for the specified cart ID.

#### Test Case: Non-Existing Cart ID
- **Input**:
    - Cart ID: 1 (Non-Existing)
- **Expected Output**:
    - Throws `CartNotFoundException`.

### 2. Create Cart

#### Test Case: Valid CartDto
- **Input**:
    - Valid CartDto
- **Expected Output**:
    - Returns CartDto for the created cart.

### 3. Add Item to Cart

#### Test Case: Existing Cart and CartItem
- **Input**:
    - Cart ID: 1
    - CartItem ID: 2
    - Quantity: 3
- **Expected Output**:
    - Returns updated CartDto with added items.

#### Test Case: Non-Existing Cart
- **Input**:
    - Cart ID: 1 (Non-Existing)
    - CartItem ID: 2
    - Quantity: 3
- **Expected Output**:
    - Throws `CartNotFoundException`.

#### Test Case: Existing Cart and Non-Existing CartItem
- **Input**:
    - Cart ID: 1
    - CartItem ID: 2 (Non-Existing)
    - Quantity: 3
- **Expected Output**:
    - Returns updated CartDto with added items.

### 4. Update Item Quantity in Cart

#### Test Case: Existing Cart and CartItem
- **Input**:
    - Cart ID: 1
    - CartItem ID: 2
    - New Quantity: 5
- **Expected Output**:
    - Returns updated CartDto with modified item quantity.

#### Test Case: Non-Existing Cart
- **Input**:
    - Cart ID: 1 (Non-Existing)
    - CartItem ID: 2
    - New Quantity: 5
- **Expected Output**:
    - Throws `CartNotFoundException`.

#### Test Case: Existing Cart and Non-Existing CartItem
- **Input**:
    - Cart ID: 1
    - CartItem ID: 2 (Non-Existing)
    - New Quantity: 5
- **Expected Output**:
    - Throws `CartItemNotFoundException`.

### 5. Remove Item from Cart

#### Test Case: Existing Cart and CartItem
- **Input**:
    - Cart ID: 1
    - CartItem ID: 2
- **Expected Output**:
    - Returns updated CartDto with removed item.

#### Test Case: Non-Existing Cart
- **Input**:
    - Cart ID: 1 (Non-Existing)
    - CartItem ID: 2
- **Expected Output**:
    - Throws `CartNotFoundException`.

#### Test Case: Existing Cart and Non-Existing CartItem
- **Input**:
    - Cart ID: 1
    - CartItem ID: 2 (Non-Existing)
- **Expected Output**:
    - Throws `CartItemNotFoundException`.

### 6. Delete Cart

#### Test Case: Existing Cart
- **Input**:
    - Cart ID: 1
- **Expected Output**:
    - Deletes the specified cart.

#### Test Case: Non-Existing Cart
- **Input**:
    - Cart ID: 1 (Non-Existing)
- **Expected Output**:
    - Throws `CartNotFoundException`.
