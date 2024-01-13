## CategoryServiceTests

### 1. Get Category by ID

#### Test Case: Success
- **Input**:
    - Category ID: 1
    - Category Name: "TestCategory"
    - Products: Empty list (assuming products are empty in this test case)
- **Expected Output**:
    - Returns CategoryDto with the specified details.

#### Test Case: Category Not Found
- **Input**:
    - Category ID: 1 (Non-Existing)
- **Expected Output**:
    - Throws `RuntimeException`.

#### Test Case: Exception in Repository
- **Input**:
    - Category ID: 1
- **Expected Output**:
    - Throws `RuntimeException` due to simulated repository error.

### 2. Get Products by Category ID

#### Test Case: Success
- **Input**:
    - Category ID: 1
    - Products: List of two products
- **Expected Output**:
    - Returns a list of ProductDto with details of the products.

#### Test Case: Category Not Found
- **Input**:
    - Category ID: 1 (Non-Existing)
- **Expected Output**:
    - Throws `RuntimeException`.

### 3. Get All Categories

#### Test Case: Success
- **Input**:
    - List of two categories
- **Expected Output**:
    - Returns a list of CategoryDto with details of the categories.

#### Test Case: Empty List
- **Input**:
    - Empty list
- **Expected Output**:
    - Returns an empty list.

#### Test Case: Data Access Exception
- **Input**:
    - Simulated DataAccessException in the repository
- **Expected Output**:
    - Throws `RuntimeException`.

### 4. Create Category

#### Test Case: Success
- **Input**:
    - CategoryDto with ID: 1, Name: "CategoryName", and empty product list
- **Expected Output**:
    - Returns CategoryDto for the created category.

#### Test Case: Null CategoryDto
- **Input**:
    - Null CategoryDto
- **Expected Output**:
    - Throws `RuntimeException`.

#### Test Case: Save Exception
- **Input**:
    - CategoryDto with ID: 1, Name: "CategoryName", and empty product list
- **Expected Output**:
    - Throws `RuntimeException` due to a simulated save exception.

### 5. Update Category

#### Test Case: Success
- **Input**:
    - Category ID: 1
    - Updated CategoryDto with Name: "UpdatedCategory" and empty product list
- **Expected Output**:
    - Returns updated CategoryDto.

#### Test Case: Category Not Found
- **Input**:
    - Category ID: 1 (Non-Existing)
- **Expected Output**:
    - Throws `CategoryNotFoundException`.

### 6. Delete Category

#### Test Case: Success
- **Input**:
    - Category ID: 1
- **Expected Output**:
    - Deletes the specified category.

#### Test Case: Category Not Found
- **Input**:
    - Category ID: 1
- **Expected Output**:
    - Throws `CategoryNotFoundException`.
