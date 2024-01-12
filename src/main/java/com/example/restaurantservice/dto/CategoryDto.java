package com.example.restaurantservice.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {

    private Long id;

    private String name;

    private List<ProductDto> productDtos;
}
