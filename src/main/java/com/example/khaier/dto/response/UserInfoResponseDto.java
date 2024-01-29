package com.example.khaier.dto.response;

import com.example.khaier.enums.Gender;
import com.example.khaier.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude
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
