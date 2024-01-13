package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.CartDto;
import com.example.restaurantservice.dto.ProductDto;
import com.example.restaurantservice.entity.Cart;
import com.example.restaurantservice.entity.CartItem;
import com.example.restaurantservice.exception.not_found.CartItemNotFoundException;
import com.example.restaurantservice.exception.not_found.CartNotFoundException;
import com.example.restaurantservice.mapper.CartMapper;
import com.example.restaurantservice.mapper.ProductMapper;
import com.example.restaurantservice.repository.CartRepository;
import com.example.restaurantservice.service.impl.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartServiceTests {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartMapper cartMapper;

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCartById_ExistingCartId_ReturnsCartDto() {
        
        Long cartId = 1L;
        Cart cart = new Cart();
        CartDto cartDto = new CartDto();

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.of(cart));
        when(cartMapper.toDto(cart)).thenReturn(cartDto);

        CartDto result = cartService.getCartById(cartId);

        assertNotNull(result);
        assertEquals(cartDto, result);

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartMapper, times(1)).toDto(cart);
    }

    @Test
    void testGetCartById_NonExistingCartId_ThrowsCartNotFoundException() {
        
        Long cartId = 1L;

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.empty());

        assertThrows(CartNotFoundException.class, () -> cartService.getCartById(cartId));

        verify(cartRepository, times(1)).findById(cartId);
    }

    @Test
    void testCreateCart_ValidCartDto_ReturnsCartDto() {
        CartDto cartDto = new CartDto();
        Cart cart = new Cart();

        when(cartMapper.toEntity(cartDto)).thenReturn(cart);
        when(cartRepository.save(cart)).thenReturn(cart);
        when(cartMapper.toDto(cart)).thenReturn(cartDto);

        CartDto result = cartService.createCart(cartDto);

        assertNotNull(result);
        assertEquals(cartDto, result);

        verify(cartMapper, times(1)).toEntity(cartDto);
        verify(cartRepository, times(1)).save(cart);
        verify(cartMapper, times(1)).toDto(cart);
    }

    @Test
    void testAddItemToCart_ExistingCartAndCartItem_ReturnsUpdatedCartDto() {
        
        Long cartId = 1L;
        Long cartItemId = 2L;
        int quantity = 3;

        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(cartItemId);
        cartItem.setQuantity(1);
        cart.setCartItems(List.of(cartItem));

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.of(cart));
        when(cartMapper.toDto(cart)).thenReturn(new CartDto());
        when(cartRepository.save(cart)).thenReturn(cart);

        CartDto result = cartService.addItemToCart(cartId, cartItemId, quantity);

        assertNotNull(result);

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartMapper, times(1)).toDto(cart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testAddItemToCart_NonExistingCart_ThrowsCartNotFoundException() {
        
        Long cartId = 1L;
        Long cartItemId = 2L;
        int quantity = 3;

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.empty());

        assertThrows(CartNotFoundException.class, () -> cartService.addItemToCart(cartId, cartItemId, quantity));

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartRepository, never()).save(any());
    }

    @Test
    void testAddItemToCart_ExistingCartAndNonExistingCartItem_ReturnsUpdatedCartDto() {
        
        Long cartId = 1L;
        Long cartItemId = 2L;
        int quantity = 3;

        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.of(cart));
        when(cartMapper.toDto(cart)).thenReturn(new CartDto());
        when(productService.getProductById(cartItemId)).thenReturn(new ProductDto());
        when(productMapper.toEntity(Collections.singletonList(any()))).thenReturn(new ArrayList<>());
        when(cartRepository.save(cart)).thenReturn(cart);

        CartDto result = cartService.addItemToCart(cartId, cartItemId, quantity);

        assertNotNull(result);

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartMapper, times(1)).toDto(cart);
        verify(productService, times(1)).getProductById(cartItemId);
        verify(productMapper, times(1)).toEntity(Collections.singletonList(any()));
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testUpdateItemQuantityInCart_ExistingCartAndCartItem_ReturnsUpdatedCartDto() {
        
        Long cartId = 1L;
        Long cartItemId = 2L;
        int newQuantity = 5;

        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(cartItemId);
        cartItem.setQuantity(1);
        cart.setCartItems(List.of(cartItem));

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.of(cart));
        when(cartMapper.toDto(cart)).thenReturn(new CartDto());
        when(cartRepository.save(cart)).thenReturn(cart);

        CartDto result = cartService.updateItemQuantityInCart(cartId, cartItemId, newQuantity);

        assertNotNull(result);

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartMapper, times(1)).toDto(cart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testUpdateItemQuantityInCart_NonExistingCart_ThrowsCartNotFoundException() {
        
        Long cartId = 1L;
        Long cartItemId = 2L;
        int newQuantity = 5;

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.empty());

        assertThrows(CartNotFoundException.class, () -> cartService.updateItemQuantityInCart(cartId, cartItemId, newQuantity));

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartRepository, never()).save(any());
    }

    @Test
    void testUpdateItemQuantityInCart_ExistingCartAndNonExistingCartItem_ThrowsCartItemNotFoundException() {
        
        Long cartId = 1L;
        Long cartItemId = 2L;
        int newQuantity = 5;

        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.of(cart));

        assertThrows(CartItemNotFoundException.class, () -> cartService.updateItemQuantityInCart(cartId, cartItemId, newQuantity));

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartRepository, never()).save(any());
    }

    @Test
    void testRemoveItemFromCart_ExistingCartAndCartItem_ReturnsUpdatedCartDto() {
        
        Long cartId = 1L;
        Long cartItemId = 2L;

        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(cartItemId);
        cartItem.setQuantity(1);
        cart.setCartItems(List.of(cartItem));

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.of(cart));
        when(cartMapper.toDto(cart)).thenReturn(new CartDto());
        when(cartRepository.save(cart)).thenReturn(cart);

        CartDto result = cartService.removeItemFromCart(cartId, cartItemId);

        assertNotNull(result);

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartMapper, times(1)).toDto(cart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testRemoveItemFromCart_NonExistingCart_ThrowsCartNotFoundException() {
        
        Long cartId = 1L;
        Long cartItemId = 2L;

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.empty());

        assertThrows(CartNotFoundException.class, () -> cartService.removeItemFromCart(cartId, cartItemId));

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartRepository, never()).save(any());
    }

    @Test
    void testRemoveItemFromCart_ExistingCartAndNonExistingCartItem_ThrowsCartItemNotFoundException() {
        
        Long cartId = 1L;
        Long cartItemId = 2L;

        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.of(cart));

        assertThrows(CartItemNotFoundException.class, () -> cartService.removeItemFromCart(cartId, cartItemId));

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartRepository, never()).save(any());
    }

    @Test
    void testDeleteCart_ExistingCart_DeletesCart() {
        
        Long cartId = 1L;
        Cart cart = new Cart();

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.of(cart));

        cartService.deleteCart(cartId);

        verify(cartRepository, times(1)).deleteById(cartId);
    }

    @Test
    void testDeleteCart_NonExistingCart_ThrowsCartNotFoundException() {
        
        Long cartId = 1L;

        when(cartRepository.findById(cartId)).thenReturn(java.util.Optional.empty());

        assertThrows(CartNotFoundException.class, () -> cartService.deleteCart(cartId));

        verify(cartRepository, times(1)).findById(cartId);
        verify(cartRepository, never()).deleteById(any());
    }
}
