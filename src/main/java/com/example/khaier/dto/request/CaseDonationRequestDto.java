package com.example.khaier.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CaseDonationRequestDto(
        @NotBlank Long userId,
        @NotBlank Long caseId,
        @NotBlank BigDecimal amount,
        @NotBlank String donationWay

) {
}
