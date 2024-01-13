## OrderServiceTests

### 1. Create Order

#### Test Case: Valid OrderDto
- **Input**:
    - Valid OrderDto
- **Expected Output**:
    - Returns the created `OrderDto`.
- **Details**:
    - The test case ensures that the service correctly converts the OrderDto to an entity, saves it, and returns the DTO of the saved order.

#### Test Case: Null OrderDto
- **Input**:
    - Null OrderDto
- **Expected Output**:
    - Throws `RuntimeException`.
- **Details**:
    - The test case checks if the service handles the null input and throws an exception.

### 2. Get All Orders

#### Test Case: Returns List of OrderDtos
- **Input**:
    - List of Orders
- **Expected Output**:
    - Returns a list of `OrderDto`.
- **Details**:
    - Verifies that the service correctly converts a list of Order entities to a list of DTOs.

### 3. Get Order by ID

#### Test Case: Existing Order ID
- **Input**:
    - Existing Order ID: 1L
- **Expected Output**:
    - Returns the `OrderDto` for the specified order ID.

#### Test Case: Non-existing Order ID
- **Input**:
    - Non-existing Order ID: 1L
- **Expected Output**:
    - Throws `OrderNotFoundException`.

### 4. Update Order

#### Test Case: Existing Order ID
- **Input**:
    - Existing Order ID: 1L
    - Updated OrderDto
- **Expected Output**:
    - Returns the updated `OrderDto`.

#### Test Case: Non-existing Order ID
- **Input**:
    - Non-existing Order ID: 1L
- **Expected Output**:
    - Throws `OrderNotFoundException`.

### 5. Update Order Status

#### Test Case: Existing Order ID
- **Input**:
    - Existing Order ID: 1L
    - Updated Order Status: PROCESSING
- **Expected Output**:
    - Returns the updated `OrderDto` with the modified order status.

#### Test Case: Non-existing Order ID
- **Input**:
    - Non-existing Order ID: 1L
- **Expected Output**:
    - Throws `OrderNotFoundException`.

### 6. Delete Order

#### Test Case: Order Exists
- **Input**:
    - Existing Order ID: 1L
- **Expected Output**:
    - Deletes the order successfully.

#### Test Case: Order Does Not Exist
- **Input**:
    - Non-existing Order ID: 1L
- **Expected Output**:
    - Throws `OrderNotFoundException`.

### 7. Check if Order is Owned by User

#### Test Case: Order Exists and Owned by User
- **Input**:
    - Existing Order ID: 1L
    - User: testUser
- **Expected Output**:
    - Returns `true`.

#### Test Case: Order Does Not Exist
- **Input**:
    - Non-existing Order ID: 1L
    - User: testUser
- **Expected Output**:
    - Throws `OrderNotFoundException`.

### Note:
- The tests use Mockito for mocking dependencies.
- The `@Mock` annotation is used to create mocks for `OrderRepository` and `OrderMapper`.
- The `@InjectMocks` annotation injects the mocks into `OrderServiceImpl`.
- Each test method covers a specific scenario, provi
