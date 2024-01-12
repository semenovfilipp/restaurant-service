package com.example.restaurantservice.exception.already_exist;

import com.example.restaurantservice.dto.CategoryDto;
import com.example.restaurantservice.entity.Category;

public class CategoryAlreadyExistsException extends AlreadyExistException{
    public CategoryAlreadyExistsException(Category aCategory) {
        super("Category " + aCategory.getName() + " already exists");
    }

    public CategoryAlreadyExistsException(CategoryDto aCategoryDto) {
        super("Category " + aCategoryDto.getName() + " already exists");
    }
}
