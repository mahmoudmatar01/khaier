package com.example.khaier.service;

import com.example.khaier.dto.request.ChangePasswordRequestDto;
import com.example.khaier.dto.response.UserInfoResponseDto;

import java.security.Principal;

public interface UserService {
    UserInfoResponseDto extractUserFromToken(String token);
    void changePassword(ChangePasswordRequestDto changePasswordRequestDto, Principal connectedUser);
}
