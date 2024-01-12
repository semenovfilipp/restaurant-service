package com.example.restaurantservice.dto;

import com.example.restaurantservice.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private CategoryDto categoryDto;

    private Image image;

}
