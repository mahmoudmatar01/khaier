package com.example.khaier.service;

import com.example.khaier.dto.request.UserLoginDto;
import com.example.khaier.dto.request.UserRegistrationDto;
import com.example.khaier.dto.response.UserResponseDto;
import com.example.khaier.entity.user.User;

public interface AuthService {
    UserResponseDto registerUser(UserRegistrationDto registerRequest);
    String loginUser(UserLoginDto loginRequest);
}
