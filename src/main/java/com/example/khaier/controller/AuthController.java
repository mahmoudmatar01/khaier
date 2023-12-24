package com.example.khaier.controller;

import com.example.khaier.dto.request.UserLoginDto;
import com.example.khaier.dto.request.UserRegistrationDto;
import com.example.khaier.entity.User;
import com.example.khaier.factory.ResponseFactory;
import com.example.khaier.models.ApiCustomResponse;
import com.example.khaier.service.Impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;
    private final ResponseFactory responseFactory;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto registerRequest) {
        User registeredUser = authService.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseFactory.createResponse(registeredUser,"User registered successfully "));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto loginRequest) {
        String authToken = authService.loginUser(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(responseFactory.createResponse(authToken,"Login successful"));
    }
}

