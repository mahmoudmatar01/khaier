package com.example.khaier.factory.impl;

import com.example.khaier.factory.ResponseFactory;
import com.example.khaier.models.ApiCustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

@Service
public class SuccessResponseFactory200 implements ResponseFactory {
    @Override
    public ApiCustomResponse<?> createResponse(Object data, String message) {
        return ApiCustomResponse
                .builder()
                .message(message)
                .data(data)
                .isSuccessful(true)
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
