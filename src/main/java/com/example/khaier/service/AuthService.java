package com.example.khaier.service;

import com.example.khaier.dto.request.UserLoginDto;
import com.example.khaier.dto.request.UserRegistrationDto;
import com.example.khaier.entity.user.User;

public interface AuthService {
    User registerUser(UserRegistrationDto registerRequest);
    String loginUser(UserLoginDto loginRequest);
}
