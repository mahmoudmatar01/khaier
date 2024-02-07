package com.example.khaier.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CaseRequestDto(
        @NotBlank String caseName,
        @NotBlank String hekma,
        @NotBlank String description,
        @NotBlank BigDecimal requiredAmount,
        @NotBlank Long categoryId

) {
}
