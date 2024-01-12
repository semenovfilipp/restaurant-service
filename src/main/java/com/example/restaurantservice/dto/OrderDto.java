package com.example.restaurantservice.dto;


import com.example.restaurantservice.entity.User;
import com.example.restaurantservice.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long orderId;
    private List<OrderItemDto> orderItemDtos;
    private User user;
    private OrderStatus orderStatus;
    private String createdBy;

    public void setCreatedBy(String name) {
        this.createdBy = name;
    }
}
