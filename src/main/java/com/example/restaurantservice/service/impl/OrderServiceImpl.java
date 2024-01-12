package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.dto.OrderDto;
import com.example.restaurantservice.entity.Order;
import com.example.restaurantservice.entity.enums.OrderStatus;
import com.example.restaurantservice.exception.not_found.OrderNotFoundException;
import com.example.restaurantservice.mapper.OrderMapper;
import com.example.restaurantservice.repository.OrderRepository;
import com.example.restaurantservice.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        try {
            log.info("Check order is not null: {}", orderDto);
            if (orderDto == null) {
                throw new RuntimeException("Order details can not be empty");
            }

            Order order = orderMapper.toEntity(orderDto);
            orderRepository.save(order);
            return orderMapper.toDto(order);

        } catch (Exception e) {
            log.error("Error while creating order: {}", orderDto);
            throw new RuntimeException("Failed to create  order", e);
        }
    }

    @Override
    public List<OrderDto> getAllOrders() {
        try {
            log.info("Try to get all orders");
            List<Order> orders = orderRepository.findAll();

            log.info("Retrieved {} orders", orders.size());
            return orderMapper.toDto(orders);
        } catch (Exception e) {
            log.error("An error occurred while retrieving all orders", e);
            throw new RuntimeException("Failed to retrieve orders", e);
        }
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        try {
            log.info("Attempting to retrieve order with id: {}", orderId);
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> {
                        log.warn("Order with id {} not found", orderId);
                        return new OrderNotFoundException(orderId);
                    });

            log.info("Retrieved order with id: {}", orderId);
            return orderMapper.toDto(order);

        } catch (Exception e) {
            log.error("An error occurred while retrieving order with id: {}", orderId, e);
            throw new RuntimeException("Failed to retrieve order with id: " + orderId, e);
        }
    }

    @Override
    public OrderDto updateOrder(Long orderId, OrderDto orderDto) {
        try {
            log.info("Update order {} with id: {}", orderDto, orderId);

            Order existingOrder = orderRepository.findById(orderId)
                    .orElseThrow(() -> {
                        log.warn("Order with id {} not found", orderId);
                        return new OrderNotFoundException(orderId);
                    });


            log.info("Applying updates to order with id: {}", orderId);
            Order updateOrder = orderMapper.toEntity(orderDto);
            Order order = Order.builder()
                    .orderId(orderId)
                    .orderStatus(updateOrder.getOrderStatus() != null ? updateOrder.getOrderStatus() : existingOrder.getOrderStatus())
                    .orderItems(updateOrder.getOrderItems() != null ? updateOrder.getOrderItems() : existingOrder.getOrderItems())
                    .user(updateOrder.getUser() != null ? updateOrder.getUser() : existingOrder.getUser())
                    .build();

            log.info("Saving updated order with id: {}", orderId);
            orderRepository.save(order);
            return orderMapper.toDto(order);
        } catch (Exception e) {
            log.error("An error occurred while updating order with id: {}", orderId, e);
            throw new RuntimeException("Failed to update order with id: " + orderId, e);
        }
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, OrderStatus newStatus) {
        log.info("Updating order status: orderId={}, newStatus={}", orderId, newStatus);
        try {
            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException(orderId));

            log.info("Set status: {}", newStatus);
            order.setOrderStatus(newStatus);

            orderRepository.save(order);
            return orderMapper.toDto(order);
        } catch (Exception e) {
            log.error("Error while updating order status: orderId={}, newStatus={}", orderId, newStatus, e);
            throw new RuntimeException("Failed to update order status", e);
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        log.info("Attempting to delete order with id: {}", orderId);
        try {
            if (orderRepository.existsById(orderId)) {
                orderRepository.deleteById(orderId);
                log.info("Order with id {} deleted successfully", orderId);

            } else {
                log.warn("Order with id {} not found", orderId);
                throw new OrderNotFoundException(orderId);
            }
        } catch (DataAccessException e) {
            log.error("An error occurred while deleting order with id: {}", orderId, e);
            throw new RuntimeException("Failed to delete order with id: " + orderId, e);
        }
    }

    @Override
    public boolean isOrderOwnedByUser(Long orderId, String username) {
        log.info("Checking if order with id {} is owned by user {}", orderId, username);
        try {
            log.info("Try to find order by id: {} ", orderId);
            orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));

            log.info("Order with id {} is owned by user {}", orderId, username);
            return true;

        } catch (OrderNotFoundException e) {
            log.error("Order with id {} not found", orderId);
            throw e;
        } catch (Exception e) {
            log.error("An error occurred while checking ownership of order with id {}", orderId, e);
            throw e;
        }
    }
}
