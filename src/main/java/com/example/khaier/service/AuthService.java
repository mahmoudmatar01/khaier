package com.example.khaier.service;

import com.example.khaier.dto.request.UserLoginRequestDto;
import com.example.khaier.dto.request.UserRegistrationRequestDto;
import com.example.khaier.dto.response.UserRegisterResponseDto;

public interface AuthService {
    UserRegisterResponseDto registerUser(UserRegistrationRequestDto registerRequest);
    String loginUser(UserLoginRequestDto loginRequest);
}
