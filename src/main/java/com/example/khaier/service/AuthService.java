package com.example.khaier.service;

import com.example.khaier.dto.request.UserLoginRequestDto;
import com.example.khaier.dto.request.UserRegistrationRequestDto;
import com.example.khaier.dto.response.UserRegisterResponseDto;

public interface AuthService {
    void registerUser(UserRegistrationRequestDto registerRequest);
    UserRegisterResponseDto registerAdmin(UserRegistrationRequestDto adminDto);
    String loginUser(UserLoginRequestDto loginRequest);
}
