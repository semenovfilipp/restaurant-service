package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.CartDto;
import com.example.restaurantservice.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CartControllerTests {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    void testGetCartById_ExistingCartId_ReturnsCartDto() throws Exception {
        Long cartId = 1L;
        CartDto cartDto = new CartDto();

        when(cartService.getCartById(cartId)).thenReturn(cartDto);

        mockMvc.perform(get("/api/v1/cart/{cartId}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").doesNotExist()) 
                .andExpect(jsonPath("$.cartItems").doesNotExist()); 

        verify(cartService, times(1)).getCartById(cartId);
    }

    @Test
    void testGetCartById_NonExistingCartId_ReturnsNotFound() throws Exception {
        Long cartId = 1L;

        when(cartService.getCartById(cartId)).thenThrow(new RuntimeException("Cart not found"));

        mockMvc.perform(get("/api/v1/cart/{cartId}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(cartService, times(1)).getCartById(cartId);
    }

    @Test
    void testCreateCart_ValidCartDto_ReturnsCreated() throws Exception {
        CartDto cartDto = new CartDto();

        when(cartService.createCart(any(CartDto.class))).thenReturn(cartDto);

        mockMvc.perform(post("/api/v1/cart/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cartDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.user").doesNotExist()) 
                .andExpect(jsonPath("$.cartItems").doesNotExist()); 

        verify(cartService, times(1)).createCart(any(CartDto.class));
    }

    @Test
    void testAddItemToCart_ExistingCartAndCartItem_ReturnsOk() throws Exception {
        Long cartId = 1L;
        Long cartItemId = 2L;
        int quantity = 3;

        CartDto cartDto = new CartDto();
        when(cartService.addItemToCart(eq(cartId), eq(cartItemId), eq(quantity))).thenReturn(cartDto);

        mockMvc.perform(post("/api/v1/cart/{cartId}/addItem/{cartItemId}", cartId, cartItemId)
                        .param("quantity", String.valueOf(quantity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").doesNotExist()) 
                .andExpect(jsonPath("$.cartItems").doesNotExist()); 

        verify(cartService, times(1)).addItemToCart(eq(cartId), eq(cartItemId), eq(quantity));
    }

    @Test
    void testUpdateItemQuantityInCart_ExistingCartAndCartItem_ReturnsOk() throws Exception {
        Long cartId = 1L;
        Long cartItemId = 2L;
        int newQuantity = 3;

        CartDto cartDto = new CartDto();
        when(cartService.updateItemQuantityInCart(eq(cartId), eq(cartItemId), eq(newQuantity))).thenReturn(cartDto);

        mockMvc.perform(put("/api/v1/cart/{cartId}/updateItem/{cartItemId}", cartId, cartItemId)
                        .param("newQuantity", String.valueOf(newQuantity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").doesNotExist()) 
                .andExpect(jsonPath("$.cartItems").doesNotExist()); 

        verify(cartService, times(1)).updateItemQuantityInCart(eq(cartId), eq(cartItemId), eq(newQuantity));
    }

    @Test
    void testRemoveItemFromCart_ExistingCartAndCartItem_ReturnsOk() throws Exception {
        Long cartId = 1L;
        Long cartItemId = 2L;

        CartDto cartDto = new CartDto();
        when(cartService.removeItemFromCart(eq(cartId), eq(cartItemId))).thenReturn(cartDto);

        mockMvc.perform(delete("/api/v1/cart/{cartId}/removeItem/{cartItemId}", cartId, cartItemId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user").doesNotExist()) 
                .andExpect(jsonPath("$.cartItems").doesNotExist()); 

        verify(cartService, times(1)).removeItemFromCart(eq(cartId), eq(cartItemId));
    }

    @Test
    void testDeleteCart_ExistingCart_ReturnsNoContent() throws Exception {
        Long cartId = 1L;

        mockMvc.perform(delete("/api/v1/cart/delete/{cartId}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(cartService, times(1)).deleteCart(eq(cartId));
    }

    @Test
    void testDeleteCart_NonExistingCart_ReturnsNotFound() throws Exception {
        Long cartId = 1L;
        doThrow(new RuntimeException("Cart not found")).when(cartService).deleteCart(eq(cartId));

        mockMvc.perform(delete("/api/v1/cart/delete/{cartId}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
