package com.example.khaier.dto.response;


import lombok.Builder;

import java.time.Duration;

@Builder
public record CampaignResponseDto(
        Long campaignId,
        String campaignName,
        String campaignAdditionalName,
        String campaignDescription,
        String campaignImageUrl,
        Duration campaignEndDay,
        Long numberOfBeneficiaries,
        double amountRequired,
        Long charityId,
        String charityName,
        String charityImgUrl
) {
}
