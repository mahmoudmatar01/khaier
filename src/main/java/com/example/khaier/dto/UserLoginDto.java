package com.example.khaier.dto;

import lombok.Builder;

@Builder
public record UserLoginDto(
         String email,
         String password
) {
}
