package com.example.restaurantservice.mapper;

import com.example.restaurantservice.dto.CategoryDto;
import com.example.restaurantservice.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDto categoryDto);
    CategoryDto toDto(Category category);
    List<Category> toEntity(List<CategoryDto> categoryDtos);
    List<CategoryDto> toDto(List<Category> categories);


}
