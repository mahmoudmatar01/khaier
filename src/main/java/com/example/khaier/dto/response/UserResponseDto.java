package com.example.khaier.dto.response;

import lombok.Builder;

@Builder
public record UserResponseDto(
        String username,
        String userEmail,
        String userImageUrl
) {
}
