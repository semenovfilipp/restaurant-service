package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.dto.CartDto;
import com.example.restaurantservice.entity.Cart;
import com.example.restaurantservice.entity.CartItem;
import com.example.restaurantservice.entity.Product;
import com.example.restaurantservice.exception.not_found.CartItemNotFoundException;
import com.example.restaurantservice.exception.not_found.CartNotFoundException;
import com.example.restaurantservice.mapper.CartMapper;
import com.example.restaurantservice.mapper.ProductMapper;
import com.example.restaurantservice.repository.CartRepository;
import com.example.restaurantservice.service.CartService;
import com.example.restaurantservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    private final ProductService productService;
    private final ProductMapper productMapper;

    // GET
    @Override
    public CartDto getCartById(Long cartId) {
        log.info("Get cart by id: {}", cartId);
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(()
                        -> new CartNotFoundException("Cart with id " + cartId + " not found"));

        return cartMapper.toDto(cart);
    }

    public CartDto createCart(CartDto cartDto) {

        log.info("Creating new cart: {}", cartDto);
        Cart cart = Cart.builder()
                .user(cartDto.getUser())
                .cartItems(new ArrayList<>())
                .build();

        log.info("Saving new cart: {}", cart);
        cartRepository.save(cart);

        return cartMapper.toDto(cart);
    }

    // PUT and POST

    @Override
    public CartDto addItemToCart(Long cartId, Long cartItemId, int quantity) {
        log.info("Adding item to cart: {}", cartId);

        Cart cart = cartRepository.findById(cartId).orElseThrow(() ->
                new CartNotFoundException("Cart not found"));

        log.info("Finding cart item: {}", cartItemId);
        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getCartItemId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found with id: " + cartItemId));

        log.info("Cart item found, refresh cart: {}", cartItem);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            /*
            Если продукт не найден
             */
            log.info("Find product by id: {}", cartItemId);
            Product product =
                    productMapper.toEntity(
                            productService.getProductById(cartItemId));
            cartItem = CartItem.builder()
                    .product(product)
                    .quantity(quantity)
                    .cart(cart)
                    .build();
            cart.getCartItems().add(cartItem);
        }

        log.info("Saving cart: {}", cart);
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Override
    public CartDto updateItemQuantityInCart(Long cartId, Long cartItemId, int newQuantity) {
        log.info("Updating item quantity in cart with id: {}", cartId);

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + cartId));

        log.info("Find cartItem by id: {}", cartItemId);
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getCartItemId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found with id: " + cartItemId));

        if (cartItem != null) {
            cartItem.setQuantity(newQuantity);
        } else {
            throw new CartItemNotFoundException("CartItem not found with id: " + cartItemId);
        }

        log.info("Saving cart: {}", cart);
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    @Override
    public CartDto removeItemFromCart(Long cartId, Long cartItemId) {
        log.info("Removing item from cart with id: {}", cartId);

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + cartId));

        log.info("Find cartItem by id: {}", cartItemId);
        CartItem cartItemToRemove = cart.getCartItems().stream()
                .filter(item -> item.getCartItemId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found with id: " + cartItemId));

        if (cartItemToRemove != null) {
            cart.getCartItems().remove(cartItemToRemove);
        } else {
            throw new CartItemNotFoundException("CartItem not found with id: " + cartItemId);
        }

        log.info("Saving cart: {}", cart);
        cartRepository.save(cart);
        return cartMapper.toDto(cart);
    }

    // DELETE
    @Override
    public void deleteCart(Long cartId) {
        log.info("Deleting cart with id: {}", cartId);
        if (cartId!= null) {
            cartRepository.deleteById(cartId);
        } else {
            throw new CartNotFoundException("Cart not found with id: " + cartId);
        }
    }
}
