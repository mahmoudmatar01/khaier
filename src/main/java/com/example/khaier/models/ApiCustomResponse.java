package com.example.khaier.models;

import lombok.Builder;

@Builder
public record ApiCustomResponse<T>(String message, T data) {}

