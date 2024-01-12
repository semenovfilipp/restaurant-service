package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.OrderDto;
import com.example.restaurantservice.entity.Order;
import com.example.restaurantservice.entity.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
    OrderDto getOrderById(Long orderId);
    OrderDto updateOrder(Long orderId, OrderDto orderDto);
    OrderDto updateOrderStatus(Long orderId, OrderStatus newStatus);
    void deleteOrder(Long orderId);

    boolean isOrderOwnedByUser(Long orderId, String username);
}
