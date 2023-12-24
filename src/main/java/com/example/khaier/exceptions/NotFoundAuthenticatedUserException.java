package com.example.khaier.exceptions;


public class NotFoundAuthenticatedUserException extends GlobalExceptionHandler {
    public NotFoundAuthenticatedUserException(String msg) {
        super(msg);
    }
}
