package com.example.khaier.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude
public record CharityResponseDto(
        Long charityId,
        String charityName,
        String charityDescription,
        String imageUrl,
        String charityLocation,
        String facebookPageUrl,
        String instagramPageUrl,
        String whatsappNumber,
        String phoneNumber,
        List<CharityCategoryResponseDto>categories,
        List<CampaignResponseDto> campaigns

) {
}
