package com.example.khaier.dto.requestDto;

import lombok.Builder;

@Builder
public record UserLoginDto(
         String email,
         String password
) {
}
