package com.example.khaier.dto.response;

import lombok.Builder;

@Builder
public record UserRegisterResponseDto(
        String username,
        String userEmail,
        String userImageUrl
) {
}
