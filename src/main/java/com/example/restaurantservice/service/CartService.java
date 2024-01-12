package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.CartDto;

public interface CartService {
    CartDto getCartById(Long cartId);

    CartDto createCart(CartDto cartDto);

    CartDto addItemToCart(Long cartId, Long cartItemId, int quantity);

    CartDto updateItemQuantityInCart(Long cartId, Long cartItemId, int newQuantity);

    CartDto removeItemFromCart(Long cartId, Long cartItemId);

    void deleteCart(Long cartId);
}
