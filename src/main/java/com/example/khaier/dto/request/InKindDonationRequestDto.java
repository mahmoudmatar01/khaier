package com.example.khaier.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record InKindDonationRequestDto(
        @NotBlank Long userId,
        @NotBlank Long organizationId,
        @NotBlank Long inKindCaseId,
        @NotBlank String phone,
        @NotBlank BigDecimal itemAmount,
        String itemName,
        @NotBlank Float lang,
        @NotBlank Float lat,
        @NotBlank String addressDescription
){}
