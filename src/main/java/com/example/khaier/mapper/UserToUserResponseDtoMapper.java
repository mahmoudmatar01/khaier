package com.example.khaier.mapper;

import com.example.khaier.dto.response.UserRegisterResponseDto;
import com.example.khaier.entity.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToUserResponseDtoMapper implements Function<User, UserRegisterResponseDto> {
    @Override
    public UserRegisterResponseDto apply(User user) {
        return UserRegisterResponseDto.builder()
                .username(user.getUsername())
                .userEmail(user.getEmail())
                .userImageUrl(user.getUserImageUrl())
                .build();
    }
}
