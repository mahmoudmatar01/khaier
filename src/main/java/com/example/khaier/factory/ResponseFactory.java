package com.example.khaier.factory;

import com.example.khaier.models.ApiCustomResponse;
import org.springframework.http.HttpStatusCode;

public interface ResponseFactory<T> {
    ApiCustomResponse<?>createResponse(T data, String message);
}
