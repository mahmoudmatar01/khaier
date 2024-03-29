package com.example.khaier.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record InKindDonationResponseDto(
        Long donationId,
        Long userId,
        String userName,
        String phone,
        Long organizationId,
        String organizationName,
        Long caseId,
        String caseTitle,
        BigDecimal itemAmount,
        String itemName,
        LocalDateTime donationTime,
        Float lang,
        Float lat,
        String addressDescription
) {
}
