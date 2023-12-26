package com.example.khaier.exceptions.globalExceptionHandler;

import com.example.khaier.exceptions.BadRequestException;
import com.example.khaier.exceptions.DelegatedAuthenticationException;
import com.example.khaier.exceptions.EmailAlreadyExistException;
import com.example.khaier.exceptions.PasswordMismatchException;
import com.example.khaier.factory.impl.ResponseFactory400;
import com.example.khaier.factory.impl.ResponseFactory401;
import com.example.khaier.factory.impl.ResponseFactory404;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.models.ApiCustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webjars.NotFoundException;

@ControllerAdvice
@RequiredArgsConstructor
class GlobalExceptionHandler {
    final SuccessResponseFactory200 successResponse;
    final ResponseFactory401 unauthorizedResponse;
    final ResponseFactory404 notFoundResponse;
    final ResponseFactory400 badRequestResponse;
    @ExceptionHandler(value = EmailAlreadyExistException.class)
    public ResponseEntity<ApiCustomResponse<?>> emailAlreadyExistException(EmailAlreadyExistException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(badRequestResponse
                        .createResponse(null,exception.getMessage()));
    }


    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiCustomResponse<?>> notFoundException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(notFoundResponse
                        .createResponse(null,exception.getMessage()));
    }



    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiCustomResponse<?>> badRequestException(BadRequestException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(badRequestResponse.createResponse(null,exception.getMessage()));
    }


    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiCustomResponse<?>> badCredentialsException(BadCredentialsException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(unauthorizedResponse.createResponse(null,exception.getMessage()));
    }
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiCustomResponse<?>> runtimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(unauthorizedResponse.createResponse(null,exception.getMessage()));
    }


    @ExceptionHandler(value = PasswordMismatchException.class)
    public ResponseEntity<ApiCustomResponse<?>> misMatchException(PasswordMismatchException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(badRequestResponse.createResponse(null,exception.getMessage()));
    }


    @ExceptionHandler(value = DelegatedAuthenticationException.class)
    public ResponseEntity<ApiCustomResponse<?>> delegatedAuthenticationException(DelegatedAuthenticationException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(unauthorizedResponse.createResponse(null,exception.getMessage()));
    }
}
