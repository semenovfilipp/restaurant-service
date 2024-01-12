package com.example.restaurantservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    void uploadImage(MultipartFile multipartFile) throws IOException;

    byte[] getImage(String imageName);
}
