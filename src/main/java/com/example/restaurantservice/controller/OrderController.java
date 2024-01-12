package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.OrderDto;
import com.example.restaurantservice.entity.enums.OrderStatus;
import com.example.restaurantservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        log.info("Get all orders");
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId) {
        log.info("Get order by id: {}", orderId);
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        log.info("Create order");
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-status/{orderId}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long orderId,
                                                      @RequestBody OrderStatus newStatus) {
        log.info("Set status {} to order with id: {}", newStatus, orderId);
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, newStatus));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId,
                                                @RequestBody OrderDto orderDto) {
        log.info("Update order with id: {}", orderId);
        return ResponseEntity.ok(orderService.updateOrder(orderId, orderDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        log.info("Delete order with id: {}", orderId);
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
