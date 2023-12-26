package com.example.khaier.service;

import com.example.khaier.dto.request.UserLoginDto;
import com.example.khaier.dto.request.UserRegistrationDto;
import com.example.khaier.dto.response.UserRegisterResponseDto;

public interface AuthService {
    UserRegisterResponseDto registerUser(UserRegistrationDto registerRequest);
    String loginUser(UserLoginDto loginRequest);
}
