package com.example.khaier.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record CampaignDonationResponseDto(
        Long donationId,
        Long userId,
        String userName,
        String userImageUrl,
        Long campaignId,
        String campaignName,
        String campaignImageUrl,
        BigDecimal donationAmount,
        LocalDateTime donationDate
) {
}
