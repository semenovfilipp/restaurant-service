package com.example.restaurantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    private Long id;

    private String name;

    private String type;

    private byte[] imageData;

    private ProductDto productDto;
}
