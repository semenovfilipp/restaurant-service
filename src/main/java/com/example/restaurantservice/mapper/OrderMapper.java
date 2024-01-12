package com.example.restaurantservice.mapper;

import com.example.restaurantservice.dto.OrderDto;
import com.example.restaurantservice.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);

    List<Order> toEntity(List<OrderDto> orderDtos);

    List<OrderDto> toDto(List<Order> orders);

}
