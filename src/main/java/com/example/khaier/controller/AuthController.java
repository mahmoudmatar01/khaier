package com.example.khaier.controller;

import com.example.khaier.dto.UserLoginDto;
import com.example.khaier.dto.UserRegistrationDto;
import com.example.khaier.entity.User;
import com.example.khaier.service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registerRequest) {
        User registeredUser = authService.registerUser(registerRequest);
        return ResponseEntity.ok("User registered successfully with ID: " + registeredUser.getUserId());
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDto loginRequest) {
        String authToken = authService.loginUser(loginRequest);
        return ResponseEntity.ok("Login successful. Token: " + authToken);
    }
}

