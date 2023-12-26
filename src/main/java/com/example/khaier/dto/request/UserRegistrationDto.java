package com.example.khaier.dto.request;

import com.example.khaier.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record UserRegistrationDto(
         @NotBlank String username,
         @NotBlank MultipartFile imageUrl,
         @NotBlank String email,
         @NotBlank String password,
         @NotBlank String confirmPassword,
         @NotBlank Gender userGender,
         @NotBlank String userPhone

) {
}
