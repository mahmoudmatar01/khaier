package com.example.khaier.dto.request;

import com.example.khaier.enums.Gender;
import lombok.Builder;

@Builder
public record UserRegistrationDto(
         String username,
         String email,
         String password,
         String confirmPassword,
         Gender userGender,
         String userPhone
) {
}
