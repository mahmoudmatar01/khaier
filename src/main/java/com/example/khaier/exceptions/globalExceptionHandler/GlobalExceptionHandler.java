package com.example.khaier.exceptions.globalExceptionHandler;

import com.example.khaier.exceptions.BadRequestException;
import com.example.khaier.exceptions.DelegatedAuthenticationException;
import com.example.khaier.exceptions.MisMatchException;
import com.example.khaier.factory.ResponseFactory;
import com.example.khaier.factory.impl.ResponseFactory400;
import com.example.khaier.factory.impl.ResponseFactory401;
import com.example.khaier.factory.impl.ResponseFactory404;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.models.ApiCustomResponse;
import com.example.khaier.security.DelegatedAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RequiredArgsConstructor
class GlobalExceptionHandler {
    final SuccessResponseFactory200 successResponse;
    final ResponseFactory401 unauthorizedResponse;
    final ResponseFactory404 notFoundResponse;
    final ResponseFactory400 badRequestResponse;
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ApiCustomResponse<?>> notFoundException(UsernameNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(notFoundResponse
                        .createResponse(exception.getMessage(), "Entity not found"));
    }
    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiCustomResponse<?>> badRequestException(BadRequestException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(badRequestResponse.createResponse(exception.getMessage(), "Bad request"));
    }
    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiCustomResponse<?>> badCredentialsException(BadCredentialsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(unauthorizedResponse.createResponse(exception.getMessage(), "Something invalid"));
    }
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiCustomResponse<?>> runtimeException(RuntimeException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(unauthorizedResponse.createResponse(exception.getMessage(), "Something invalid"));
    }
    @ExceptionHandler(value = MisMatchException.class)
    public ResponseEntity<ApiCustomResponse<?>> misMatchException(MisMatchException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(badRequestResponse.createResponse(exception.getMessage(), "Something invalid"));
    }
    @ExceptionHandler(value = DelegatedAuthenticationException.class)
    public ResponseEntity<ApiCustomResponse<?>> delegatedAuthenticationException(DelegatedAuthenticationException exception) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(badRequestResponse.createResponse(exception.getMessage(), "Something invalid"));
    }
}
