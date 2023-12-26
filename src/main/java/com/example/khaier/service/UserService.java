package com.example.khaier.service;

import com.example.khaier.dto.response.UserInfoResponseDto;

public interface UserService {
    UserInfoResponseDto extractUserFromToken(String token);
}
