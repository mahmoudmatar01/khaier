package com.example.khaier.exceptions;


public class NotFoundAuthenticatedUserException extends RuntimeException {
    public NotFoundAuthenticatedUserException(String msg) {
        super(msg);
    }
}
