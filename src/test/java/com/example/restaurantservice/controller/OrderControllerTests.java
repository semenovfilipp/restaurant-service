package com.example.restaurantservice.controller;

import com.example.restaurantservice.dto.OrderDto;
import com.example.restaurantservice.entity.enums.OrderStatus;
import com.example.restaurantservice.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class OrderControllerTests {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void testGetAllOrders_ReturnsListOfOrderDto() throws Exception {
        List<OrderDto> orderDtos = Arrays.asList(new OrderDto(), new OrderDto());

        when(orderService.getAllOrders()).thenReturn(orderDtos);

        mockMvc.perform(get("/api/v1/order/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").doesNotExist()) 
                .andExpect(jsonPath("$[0].status").doesNotExist()); 

        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    void testGetOrderById_ExistingOrderId_ReturnsOrderDto() throws Exception {
        Long orderId = 1L;
        OrderDto orderDto = new OrderDto();

        when(orderService.getOrderById(orderId)).thenReturn(orderDto);

        mockMvc.perform(get("/api/v1/order/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist()) 
                .andExpect(jsonPath("$.status").doesNotExist()); 

        verify(orderService, times(1)).getOrderById(orderId);
    }

    @Test
    void testCreateOrder_ValidOrderDto_ReturnsOk() throws Exception {
        OrderDto orderDto = new OrderDto();

        when(orderService.createOrder(any(OrderDto.class))).thenReturn(orderDto);

        mockMvc.perform(post("/api/v1/order/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist()) 
                .andExpect(jsonPath("$.status").doesNotExist()); 

        verify(orderService, times(1)).createOrder(any(OrderDto.class));
    }

    @Test
    void testUpdateOrderStatus_ExistingOrderId_ReturnsOk() throws Exception {
        Long orderId = 1L;
        OrderStatus newStatus = OrderStatus.PROCESSING;
        OrderDto orderDto = new OrderDto();

        when(orderService.updateOrderStatus(eq(orderId), eq(newStatus))).thenReturn(orderDto);

        mockMvc.perform(put("/api/v1/order/update-status/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStatus)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist()) 
                .andExpect(jsonPath("$.status").doesNotExist()); 

        verify(orderService, times(1)).updateOrderStatus(eq(orderId), eq(newStatus));
    }

    @Test
    void testUpdateOrder_ExistingOrderId_ReturnsOk() throws Exception {
        Long orderId = 1L;
        OrderDto orderDto = new OrderDto();

        when(orderService.updateOrder(eq(orderId), any(OrderDto.class))).thenReturn(orderDto);

        mockMvc.perform(put("/api/v1/order/update/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist()) 
                .andExpect(jsonPath("$.status").doesNotExist()); 

        verify(orderService, times(1)).updateOrder(eq(orderId), any(OrderDto.class));
    }

    @Test
    void testDeleteOrder_ExistingOrderId_ReturnsNoContent() throws Exception {
        Long orderId = 1L;

        mockMvc.perform(delete("/api/v1/order/delete/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(orderService, times(1)).deleteOrder(eq(orderId));
    }
}
