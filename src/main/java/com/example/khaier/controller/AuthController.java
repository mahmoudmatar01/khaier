package com.example.khaier.controller;

import com.example.khaier.dto.request.UserLoginRequestDto;
import com.example.khaier.dto.request.UserRegistrationRequestDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.Impl.AuthServiceImpl;
import com.example.khaier.service.Impl.UserImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;
    private final SuccessResponseFactory200 responseFactory;
    private final UserImageServiceImpl userImageService;

    @PostMapping(value= "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> registerUser(@ModelAttribute UserRegistrationRequestDto registerRequest) {

        var registeredUser = authService.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(registeredUser,"User registered successfully "));
    }

    @PostMapping(value= "/admin/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> registerAdmin(@ModelAttribute UserRegistrationRequestDto registerRequest) {

        var admin = authService.registerAdmin(registerRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(admin,"Admin registered successfully "));
    }

    @PostMapping(value = "/login", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> loginUser( @ModelAttribute UserLoginRequestDto loginRequest) {

        String authToken = authService.loginUser(loginRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(authToken, "Login successful"));
    }
    @GetMapping(value = "/image/{title}", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getImage(@PathVariable String title){
        byte[] imageData = userImageService.downloadImage(title);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}

