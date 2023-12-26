package com.example.khaier.dto.response;

import com.example.khaier.enums.Gender;
import com.example.khaier.enums.Role;
import lombok.Builder;

@Builder
public record UserInfoResponseDto(
        Long userId,
        String name,
        String email,
        String phone,
        String imageUrl,
        Gender gender,
        Role role
) {
}
