package com.example.khaier.factory.impl;

import com.example.khaier.factory.ResponseFactory;
import com.example.khaier.models.ApiCustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseFactory401 implements ResponseFactory {
    @Override
    public ApiCustomResponse<?> createResponse(Object data, String message) {
        return ApiCustomResponse
                .builder()
                .message(message)
                .isSuccessful(false)
                .data(null)
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .build();
    }
}
