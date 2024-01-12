package com.example.restaurantservice.controller;

import com.example.restaurantservice.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ImageControllerTests {

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ImageController imageController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    void testUploadImage_ValidImageFile_ReturnsOk() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile("image", "test-image.png",
                "image/png", "image content".getBytes());

        mockMvc.perform(multipart("/api/v1/image/upload")
                        .file(imageFile))
                .andExpect(status().isOk())
                .andExpect(content().string("Image uploaded successfully"));

        verify(imageService, times(1)).uploadImage(eq(imageFile));
    }

    @Test
    void testUploadImage_InvalidFileType_ReturnsBadRequest() throws Exception {
        MockMultipartFile invalidFile = new MockMultipartFile("image", "test-file.txt",
                "text/plain", "file content".getBytes());

        mockMvc.perform(multipart("/api/v1/image/upload")
                        .file(invalidFile))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid file type. Please upload an image"));

        verify(imageService, never()).uploadImage(any());
    }

    @Test
    void testUploadImage_ServiceFails_ReturnsInternalServerError() throws Exception {
        MockMultipartFile imageFile = new MockMultipartFile("image", "test-image.png",
                "image/png", "image content".getBytes());

        doThrow(new IOException("Failed to upload image")).when(imageService).uploadImage(eq(imageFile));

        mockMvc.perform(multipart("/api/v1/image/upload")
                        .file(imageFile))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to upload image"));

        verify(imageService, times(1)).uploadImage(eq(imageFile));
    }

    @Test
    void testDownloadImage_ExistingFileName_ReturnsImageContent() throws Exception {
        String fileName = "test-image.png";
        byte[] imageData = "image content".getBytes();

        when(imageService.getImage(eq(fileName))).thenReturn(imageData);

        mockMvc.perform(get("/api/v1/image/get/{fileName}", fileName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_PNG))
                .andExpect(content().bytes(imageData));

        verify(imageService, times(1)).getImage(eq(fileName));
    }
}
