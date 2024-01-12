package com.example.restaurantservice.mapper;

import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    List<ProductDto> toDto(List<Product> products);

    List<Product> toEntity(List<ProductDto> productDtos);


}

