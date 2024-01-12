package com.example.restaurantservice.dto;

import com.example.restaurantservice.entity.CartItem;
import com.example.restaurantservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CartDto {
    private Long cartId;
    private List<CartItem> cartItems;
    private User user;
}
