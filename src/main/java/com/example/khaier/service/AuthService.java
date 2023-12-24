package com.example.khaier.service;

import com.example.khaier.dto.requestDto.UserLoginDto;
import com.example.khaier.dto.requestDto.UserRegistrationDto;
import com.example.khaier.entity.User;

public interface AuthService {
    User registerUser(UserRegistrationDto registerRequest);
    String loginUser(UserLoginDto loginRequest);
}
