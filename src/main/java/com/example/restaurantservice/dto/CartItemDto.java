package com.example.restaurantservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CartItemDto {
    private Long cartItemId;
    private CartDto cartDto;
    private ProductDto productDto;
    private int quantity;
}
