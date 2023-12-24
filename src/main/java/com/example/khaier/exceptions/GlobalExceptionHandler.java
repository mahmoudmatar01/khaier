package com.example.khaier.exceptions;

public class GlobalExceptionHandler extends RuntimeException{
    public GlobalExceptionHandler(String message) {
        super(message);
    }
}
