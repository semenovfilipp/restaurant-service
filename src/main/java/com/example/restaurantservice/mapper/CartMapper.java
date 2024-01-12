package com.example.restaurantservice.mapper;

import com.example.restaurantservice.dto.CartDto;
import com.example.restaurantservice.entity.Cart;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toEntity(CartDto cartDto);
    CartDto toDto(Cart cart);
}
