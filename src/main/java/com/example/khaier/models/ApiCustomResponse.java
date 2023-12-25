package com.example.khaier.models;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ApiCustomResponse<T>(String message, T data, Integer statusCode, boolean isSuccessful) {}

