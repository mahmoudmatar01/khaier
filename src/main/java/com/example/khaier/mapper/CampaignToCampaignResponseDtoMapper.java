package com.example.khaier.mapper;

import com.example.khaier.dto.response.DonationCampaignResponseDto;
import com.example.khaier.entity.DonationCampaign;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

@Component
@Repository
public class CampaignToCampaignResponseDtoMapper implements Function<DonationCampaign, DonationCampaignResponseDto> {
    @Override
    public DonationCampaignResponseDto apply(DonationCampaign donationCampaign) {
        return DonationCampaignResponseDto.builder()
                .campaignId(donationCampaign.getCampaignId())
                .campaignName(donationCampaign.getCampaignName())
                .campaignAdditionalName(donationCampaign.getCampaignAdditionalName())
                .campaignDescription(donationCampaign.getCampaignDescription())
                .campaignImageUrl(donationCampaign.getCampaignImage().getUrl())
                .campaignEndDay(Duration.between(LocalDateTime.now(),donationCampaign.getCampaignEndDay()))
                .amountRequired(donationCampaign.getAmountRequired())
                .numberOfBeneficiaries(donationCampaign.getNumberOfBeneficiaries())
                .charityId(donationCampaign.getCharitableOrganization().getOrgId())
                .charityName(donationCampaign.getCharitableOrganization().getOrgName())
                .charityImgUrl(donationCampaign.getCharitableOrganization().getCharitableOrgImage().getUrl())
                .build();
    }
}
