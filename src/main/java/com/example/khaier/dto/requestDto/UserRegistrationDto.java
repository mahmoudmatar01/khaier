package com.example.khaier.dto.requestDto;

import lombok.Builder;

@Builder
public record UserRegistrationDto(
         String username,
         String email,
         String password
) {
}
