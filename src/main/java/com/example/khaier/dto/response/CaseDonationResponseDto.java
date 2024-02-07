package com.example.khaier.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record CaseDonationResponseDto(
        Long userId,
        String userName,
        String userImageUrl,
        Long caseId,
        String caseTitle,
        Long charityId,
        String charityName,
        String charityImageUrl,
        BigDecimal amount,
        String donationWay,
        LocalDateTime donationTime
) {

}
