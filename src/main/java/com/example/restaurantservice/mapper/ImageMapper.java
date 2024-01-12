package com.example.restaurantservice.mapper;

import com.example.restaurantservice.dto.ImageDto;
import com.example.restaurantservice.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    ImageDto toDto(Image image);

    Image toEntity(ImageDto imageDto);
}
