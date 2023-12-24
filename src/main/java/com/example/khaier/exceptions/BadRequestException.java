package com.example.khaier.exceptions;

public class BadRequestException extends GlobalExceptionHandler{
    public BadRequestException(String message) {
        super(message);
    }
}
