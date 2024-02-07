package com.example.khaier.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CharityCategoryRequestDto(
        @NotBlank String title,
        @NotBlank Long charityId
) {
}
