package com.example.restaurantservice.service.impl;

import com.example.restaurantservice.entity.Image;
import com.example.restaurantservice.exception.not_found.ImageNotFoundException;
import com.example.restaurantservice.repository.ImageRepository;
import com.example.restaurantservice.service.ImageService;
import com.example.restaurantservice.util.ImageUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public void uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            log.warn("Error while saving file");
            throw new RuntimeException("Failed to store empty file");
        }

        log.info("Trying to save image");
        Image savedImageData =
                imageRepository.save(
                        Image.builder()
                                .name(file.getOriginalFilename())
                                .type(file.getContentType())
                                .imageData(ImageUtils.compressImage(file.getBytes()))
                                .build());
        log.info("Image saved: {}", savedImageData);
    }


    @Transactional
    @Override
    public byte[] getImage(String imageName) {
        return imageRepository.findByName(imageName)
                .map(image -> ImageUtils.decompressedImage(image.getImageData()))
                .orElseThrow(() -> {
                    log.warn("Image with name '{}' not found", imageName);
                    throw new ImageNotFoundException("Image not found");
                });
    }
}
