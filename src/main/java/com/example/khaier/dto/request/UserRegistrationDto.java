package com.example.khaier.dto.request;

import com.example.khaier.enums.Gender;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record UserRegistrationDto(
         String username,

         MultipartFile imageUrl,
         String email,
         String password,
         String confirmPassword,
         Gender userGender,
         String userPhone
) {
}
