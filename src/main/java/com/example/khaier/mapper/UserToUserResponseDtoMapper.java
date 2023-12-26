package com.example.khaier.mapper;

import com.example.khaier.dto.response.UserResponseDto;
import com.example.khaier.entity.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToUserResponseDtoMapper implements Function<User, UserResponseDto> {
    @Override
    public UserResponseDto apply(User user) {
        return UserResponseDto.builder()
                .username(user.getUsername())
                .userEmail(user.getEmail())
                .userImageUrl(user.getUserImageUrl())
                .build();
    }
}
