package com.example.khaier.dto;

import lombok.Data;

@Data
public record UserRegistrationDto(
         String username,
         String email,
         String password
) {
}
