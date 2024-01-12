package com.example.restaurantservice.service;

import com.example.restaurantservice.entity.Image;
import com.example.restaurantservice.exception.not_found.ImageNotFoundException;
import com.example.restaurantservice.repository.ImageRepository;
import com.example.restaurantservice.service.impl.ImageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImageServiceTests {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageServiceImpl imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadImage_ValidFile_SuccessfullyUploaded() throws IOException {
        
        MockMultipartFile file = new MockMultipartFile("image", "test.jpg", "image/jpeg", "some image".getBytes());

        when(imageRepository.save(any())).thenReturn(new Image());

        assertDoesNotThrow(() -> imageService.uploadImage(file));

        verify(imageRepository, times(1)).save(any());
    }

    @Test
    void testUploadImage_EmptyFile_ThrowsRuntimeException() throws IOException {
        
        MockMultipartFile file = new MockMultipartFile("image", "", "image/jpeg", new byte[0]);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> imageService.uploadImage(file));
        assertEquals("Failed to store empty file", exception.getMessage());

        verify(imageRepository, never()).save(any());
    }

    @Test
    void testGetImage_ExistingImageName_ReturnsImageData() {
        
        String imageName = "test.jpg";
        Image image = new Image();
        image.setImageData(new byte[]{1, 2, 3});

        when(imageRepository.findByName(imageName)).thenReturn(Optional.of(image));

        byte[] result = imageService.getImage(imageName);

        assertNotNull(result);
        assertArrayEquals(image.getImageData(), result);

        verify(imageRepository, times(1)).findByName(imageName);
    }

    @Test
    void testGetImage_NonExistingImageName_ThrowsImageNotFoundException() {
        
        String imageName = "nonexistent.jpg";

        when(imageRepository.findByName(imageName)).thenReturn(Optional.empty());

        ImageNotFoundException exception = assertThrows(ImageNotFoundException.class, () -> imageService.getImage(imageName));
        assertEquals("Image not found", exception.getMessage());

        verify(imageRepository, times(1)).findByName(imageName);
    }
}
