package com.example.khaier.mapper;

import com.example.khaier.dto.request.CampaignRequestDto;
import com.example.khaier.entity.CampaignImage;
import com.example.khaier.entity.CharitableOrganization;
import com.example.khaier.entity.Campaign;
import com.example.khaier.repository.CharityRepository;
import com.example.khaier.service.Impl.CampaignImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CampaignRequestDtoToCampaignMapper implements Function<CampaignRequestDto, Campaign> {
    private final CampaignImageService campaignImageService;
    private final CharityRepository charityRepository;
    @Override
    public Campaign apply(CampaignRequestDto donationCampaignRequestDto) {
        CharitableOrganization charitableOrganization=findCharity(donationCampaignRequestDto.charityId());
        CampaignImage image=uploadImage(donationCampaignRequestDto.image());
        return Campaign.builder()
                .campaignName(donationCampaignRequestDto.campaignName())
                .campaignDescription(donationCampaignRequestDto.campaignDescription())
                .campaignAdditionalName(donationCampaignRequestDto.campaignAdditionalName())
                .campaignImage(image)
                .campaignEndDay(donationCampaignRequestDto.campaignEndDay())
                .numberOfBeneficiaries(donationCampaignRequestDto.numberOfBeneficiaries())
                .amountRequired(donationCampaignRequestDto.amountRequired())
                .charitableOrganization(charitableOrganization)
                .build();
    }

    private CharitableOrganization findCharity(Long charityId){
        return charityRepository.findById(charityId).orElseThrow(
                ()-> new NotFoundException("Charity with id : "+charityId+" not found!")
        );
    }

    private CampaignImage uploadImage(MultipartFile image){
        try {
            return campaignImageService.uploadImage(image);
        } catch (IOException e) {
            throw new RuntimeException("Image can't upload because : "+e.getMessage());
        }
    }
}

