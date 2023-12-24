package com.example.khaier.dto.request;

import lombok.Builder;

@Builder
public record UserRegistrationDto(
         String username,
         String email,
         String password
) {
}
