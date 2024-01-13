## ProductServiceTests

### 1. Get Product by ID

#### Test Case: Return ProductDto
- **Input**:
    - Product ID: 1L
    - Existing Product in the repository
- **Expected Output**:
    - Returns a non-null `ProductDto` for the specified product ID.

#### Test Case: Product Not Found
- **Input**:
    - Product ID: 1L
    - Product not found in the repository
- **Expected Output**:
    - Throws `ProductNotFoundException`.

#### Test Case: Exception in Repository
- **Input**:
    - Product ID: 1L
    - Simulated exception during repository operation
- **Expected Output**:
    - Throws `RuntimeException` with a specific message.

### 2. Get All Products

#### Test Case: Return List of Products
- **Input**:
    - List of mock products
- **Expected Output**:
    - Returns a non-empty list of `ProductDto`.

#### Test Case: Exception in Repository
- **Input**:
    - Simulated exception during repository operation
- **Expected Output**:
    - Throws `RuntimeException` with a specific message.

### 3. Create Product

#### Test Case: Return ProductDto
- **Input**:
    - ProductDto with valid data
- **Expected Output**:
    - Returns the created `ProductDto`.

#### Test Case: Exception in Repository
- **Input**:
    - Simulated exception during repository operation
- **Expected Output**:
    - Throws `RuntimeException` with a specific message.

### 4. Update Product

#### Test Case: Successful Update
- **Input**:
    - Existing Product ID: 1L
    - Updated ProductDto
- **Expected Output**:
    - Returns the updated `ProductDto`.

### 5. Delete Product

#### Test Case: Successful Delete
- **Input**:
    - Existing Product ID: 1L
- **Expected Output**:
    - Deletes the product successfully.

#### Test Case: Product Not Found
- **Input**:
    - Non-existing Product ID: 1L
- **Expected Output**:
    - Throws `ProductNotFoundException`.

#### Test Case: Exception On Delete
- **Input**:
    - Existing Product ID: 1L
    - Simulated exception during repository delete operation
- **Expected Output**:
    - Throws `DataAccessException` with a specific message.

### Note:
- The tests utilize Mockito for mocking dependencies.
- The `@Mock` annotation is used to create a mock of the `ProductRepository` and `CategoryMapper`.
- The `@InjectMocks` annotation is used to inject the mocks into the `ProductServiceImpl`.
- Each test method represents a specific scenario with defined input and expected output.
- The `verify` and `when` statements are used to ensure that specific methods are called on the mocked dependencies and define their behavior.

