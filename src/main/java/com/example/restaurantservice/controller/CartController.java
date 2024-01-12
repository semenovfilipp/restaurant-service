package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.CartDto;
import com.example.restaurantservice.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartService cartService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCartById(@PathVariable Long cartId) {
        log.info("Getting cart by id: {}", cartId);
        return new ResponseEntity<>(cartService.getCartById(cartId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto) {
        log.info("Request to create cart: {}", cartDto);
        return new ResponseEntity<>(cartService.createCart(cartDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{cartId}/addItem/{cartItemId}")
    public ResponseEntity<CartDto> addItemToCart(@PathVariable Long cartId,
                                                 @PathVariable Long cartItemId,
                                                 @RequestParam int quantity) {
        log.info("Adding item to cart: {}", cartId);
        return new ResponseEntity<>(cartService.addItemToCart(cartId, cartItemId, quantity), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{cartId}/updateItem/{cartItemId}")
    public ResponseEntity<CartDto> updateItemQuantityInCart(@PathVariable Long cartId,
                                                            @PathVariable Long cartItemId,
                                                            @RequestParam int newQuantity) {
        log.info("Request to update item quantity in cart with id: {}", cartId);
        return new ResponseEntity<>(cartService.updateItemQuantityInCart(cartId, cartItemId, newQuantity), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{cartId}/removeItem/{cartItemId}")
    public ResponseEntity<CartDto> removeItemFromCart(@PathVariable Long cartId,
                                                      @PathVariable Long cartItemId) {
        log.info("Request to remove item from cart with id: {}", cartId);
        return new ResponseEntity<>(cartService.removeItemFromCart(cartId, cartItemId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        log.info("Request to delete cart with id: {}", cartId);
        cartService.deleteCart(cartId);
        log.info("Cart deleted with id {} successfully", cartId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
