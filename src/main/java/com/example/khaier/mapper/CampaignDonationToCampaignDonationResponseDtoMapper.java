package com.example.khaier.mapper;

import com.example.khaier.dto.response.CampaignDonationResponseDto;
import com.example.khaier.entity.CampaignDonation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CampaignDonationToCampaignDonationResponseDtoMapper implements Function<CampaignDonation, CampaignDonationResponseDto> {
    @Override
    public CampaignDonationResponseDto apply(CampaignDonation campaignDonation) {
        return CampaignDonationResponseDto
                .builder()
                .donationId(campaignDonation.getDonationId())
                .userId(campaignDonation.getUser().getUserId())
                .userName(campaignDonation.getUser().getUsername())
                .userImageUrl(campaignDonation.getUser().getUserImageUrl())
                .campaignId(campaignDonation.getCampaign().getCampaignId())
                .campaignName(campaignDonation.getCampaign().getCampaignName())
                .campaignImageUrl(campaignDonation.getCampaign().getCampaignImage().getUrl())
                .donationAmount(campaignDonation.getAmount())
                .donationDate(campaignDonation.getDonationTime())
                .build();
    }
}
