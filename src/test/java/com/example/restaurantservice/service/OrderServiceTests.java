package com.example.restaurantservice.service;

import com.example.restaurantservice.dto.OrderDto;
import com.example.restaurantservice.entity.Order;
import com.example.restaurantservice.entity.enums.OrderStatus;
import com.example.restaurantservice.exception.not_found.OrderNotFoundException;
import com.example.restaurantservice.mapper.OrderMapper;
import com.example.restaurantservice.repository.OrderRepository;
import com.example.restaurantservice.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*
    Test for createOrder
     */
    @Test
    void testCreateOrder_ValidOrderDto_ReturnsOrderDto() {
        OrderDto orderDto = new OrderDto();
        Order order = new Order();

        when(orderMapper.toEntity(orderDto)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.toDto(order)).thenReturn(orderDto);

        OrderDto result = orderService.createOrder(orderDto);

        assertNotNull(result);
        assertEquals(orderDto, result);

        verify(orderMapper, times(1)).toEntity(orderDto);
        verify(orderRepository, times(1)).save(order);
        verify(orderMapper, times(1)).toDto(order);
    }

    @Test
    void testCreateOrder_NullOrderDto_ThrowsRuntimeException() {
        OrderDto orderDto = null;

        assertThrows(RuntimeException.class, () -> orderService.createOrder(orderDto));

        verify(orderRepository, never()).save(any());
    }

    /*
    Test for  getAllOrders
     */
    @Test
    void testGetAllOrders_ReturnsListOfOrderDtos() {
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> orders = Arrays.asList(order1, order2);
        List<OrderDto> orderDtos = Arrays.asList(new OrderDto(), new OrderDto());

        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.toDto(orders)).thenReturn(orderDtos);

        List<OrderDto> result = orderService.getAllOrders();

        assertEquals(2, result.size());

        verify(orderRepository, times(1)).findAll();
        verify(orderMapper, times(1)).toDto(orders);
    }

    /*
    Test for getOrderById
     */
    @Test
    void getOrderById_ExistingOrderId_ReturnsOrderDto() {
        Long orderId = 1L;
        Order order = new Order();
        OrderDto orderDto = new OrderDto();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderMapper.toDto(order)).thenReturn(orderDto);

        OrderDto result = orderService.getOrderById(orderId);

        assertNotNull(result);
        assertEquals(orderDto, result);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderMapper, times(1)).toDto(order);
    }


    @Test
    void getOrderById_NonExistingOrderId_ThrowsOrderNotFoundException() {
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.getOrderById(orderId));

        verify(orderRepository, times(1)).findById(orderId);
    }

    /*
    Test for updateOrder
     */
    @Test
    void updateOrder_ExistingOrderId_ReturnsUpdatedOrderDto() {
        Long orderId = 1L;
        Order existingOrder = new Order();
        OrderDto updatedOrderDto = new OrderDto();
        Order updatedOrder = new Order();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderMapper.toEntity(updatedOrderDto)).thenReturn(updatedOrder);
        when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);
        when(orderMapper.toDto(updatedOrder)).thenReturn(updatedOrderDto);


        OrderDto result = orderService.updateOrder(orderId, updatedOrderDto);


        assertNotNull(result);
        assertEquals(updatedOrderDto, result);

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderMapper, times(1)).toEntity(updatedOrderDto);
        verify(orderRepository, times(1)).save(updatedOrder);
        verify(orderMapper, times(1)).toDto(updatedOrder);
    }

    @Test
    void updateOrder_NonExistingOrderId_ThrowsOrderNotFoundException() {
        Long orderId = 1L;
        OrderDto updatedOrderDto = new OrderDto();

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrder(orderId, updatedOrderDto));


        verify(orderRepository, times(1)).findById(orderId);
        verify(orderMapper, never()).toEntity(updatedOrderDto);
        verify(orderRepository, never()).save(any());
        verify(orderMapper, never()).toDto(Collections.singletonList(any()));
    }

    /*
    Test for updateStatus
     */
    @Test
    void testUpdateOrderStatus_ExistingOrderId_ReturnsUpdatedOrderDto() {
        Long orderId = 1L;
        Order existingOrder = new Order();
        OrderDto updatedOrderDto = new OrderDto();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderMapper.toDto(existingOrder)).thenReturn(updatedOrderDto);


        OrderDto result = orderService.updateOrderStatus(orderId, OrderStatus.PROCESSING);


        assertNotNull(result);
        assertEquals(updatedOrderDto, result);
        assertEquals(OrderStatus.PROCESSING, existingOrder.getOrderStatus());


        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, times(1)).save(existingOrder);
        verify(orderMapper, times(1)).toDto(existingOrder);
    }

    @Test
    void testUpdateOrderStatus_NonExistingOrderId_ThrowsOrderNotFoundException() {
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.updateOrderStatus(orderId, OrderStatus.PROCESSING));

        verify(orderRepository, times(1)).findById(orderId);
        verify(orderRepository, never()).save(any());
        verify(orderMapper, never()).toDto(Collections.singletonList(any()));
    }
    /*
    Test for deleteOrder
     */
    @Test
    void deleteOrder_OrderExists_DeletedSuccessfully() {
        Long orderId = 1L;

        when(orderRepository.existsById(orderId)).thenReturn(true);

        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    void deleteOrder_OrderDoesNotExist_ThrowsOrderNotFoundException() {
        Long orderId = 1L;

        when(orderRepository.existsById(orderId)).thenReturn(false);

        assertThrows(OrderNotFoundException.class, () -> orderService.deleteOrder(orderId));
    }

    /*
    Test for isOrderOwnerByUser
     */
    @Test
    void isOrderOwnedByUser_OrderExistsAndOwnedByUser_ReturnsTrue() {
        Long orderId = 1L;
        String username = "testUser";

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(new Order()));

        assertTrue(orderService.isOrderOwnedByUser(orderId, username));
    }

    @Test
    void isOrderOwnedByUser_OrderDoesNotExist_ThrowsOrderNotFoundException() {
        Long orderId = 1L;
        String username = "testUser";

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.isOrderOwnedByUser(orderId, username));
    }
}
