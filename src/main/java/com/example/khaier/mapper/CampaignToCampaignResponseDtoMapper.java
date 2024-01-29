package com.example.khaier.mapper;

import com.example.khaier.dto.response.CampaignResponseDto;
import com.example.khaier.entity.Campaign;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.function.Function;

@Component
@Repository
public class CampaignToCampaignResponseDtoMapper implements Function<Campaign, CampaignResponseDto> {
    @Override
    public CampaignResponseDto apply(Campaign donationCampaign) {
        return CampaignResponseDto.builder()
                .campaignId(donationCampaign.getCampaignId())
                .campaignName(donationCampaign.getCampaignName())
                .campaignAdditionalName(donationCampaign.getCampaignAdditionalName())
                .campaignDescription(donationCampaign.getCampaignDescription())
                .campaignImageUrl(donationCampaign.getCampaignImage().getUrl())
                .campaignEndDay(Duration.between(LocalDateTime.now(),donationCampaign.getCampaignEndDay()).toDays()<=0?"تم الانتهاء منها":"متبقي : " +(Duration.between(LocalDateTime.now(),donationCampaign.getCampaignEndDay()).toDays()+1)+" ايام ")
                .amountRequired(donationCampaign.getAmountRequired())
                .numberOfBeneficiaries(donationCampaign.getNumberOfBeneficiaries())
                .charityId(donationCampaign.getCharitableOrganization().getOrgId())
                .charityName(donationCampaign.getCharitableOrganization().getOrgName())
                .charityImgUrl(donationCampaign.getCharitableOrganization().getCharitableOrgImage().getUrl())
                .build();
    }
}
