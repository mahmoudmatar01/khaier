package com.example.khaier.service.Impl;

import com.example.khaier.dto.response.UserInfoResponseDto;
import com.example.khaier.entity.user.User;
import com.example.khaier.exceptions.BadRequestException;
import com.example.khaier.security.JwtTokenUtils;
import com.example.khaier.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtTokenUtils jwtTokenUtils;
    @Override
    public UserInfoResponseDto extractUserFromToken(String token) {
        if(!jwtTokenUtils.validateToken(token)){
            throw new BadRequestException("Invalid token");
        }
        return jwtTokenUtils.extractUserFromToken(token);
    }
}
