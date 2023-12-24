package com.example.khaier.dto.request;

import lombok.Builder;

@Builder
public record UserLoginDto(
         String email,
         String password
) {
}
