package com.example.khaier.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ChangePasswordRequestDto(
        @NotBlank String email,
        @NotBlank String newPassword,
        @NotBlank String confirmationPassword
) {
}
