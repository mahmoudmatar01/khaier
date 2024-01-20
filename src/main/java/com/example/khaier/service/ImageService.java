package com.example.khaier.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService<T> {
    T uploadImage(MultipartFile file) throws IOException;
    byte[] downloadImage(String title);
    String generateUrl(String title);
}
