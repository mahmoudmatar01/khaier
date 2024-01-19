package com.example.khaier.mapper;

import com.example.khaier.dto.request.CharityRequestDto;
import com.example.khaier.entity.CharitableOrgImage;
import com.example.khaier.entity.CharitableOrganization;
import com.example.khaier.service.Impl.CharityImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CharityRequestDtoToCharityMapper implements Function<CharityRequestDto, CharitableOrganization> {
    private final CharityImageService charityImageService;
    @Override
    public CharitableOrganization apply(CharityRequestDto charityRequestDto) {
        try {
            CharitableOrgImage charitableOrgImage=charityImageService.uploadImage(charityRequestDto.image());
            return CharitableOrganization
                    .builder()
                    .orgName(charityRequestDto.charityName())
                    .description(charityRequestDto.charityDescription())
                    .charitableOrgImage(charitableOrgImage)
                    .facebookUrl(charityRequestDto.facebookPageUrl())
                    .location(charityRequestDto.charityLocation())
                    .instagramUrl(charityRequestDto.instagramPageUrl())
                    .orgPhoneNumber(charityRequestDto.phoneNumber())
                    .orgWhatsappNumber(charityRequestDto.whatsappNumber())
                    .donationCampaigns(new ArrayList<>())
                    .donationCategories(new ArrayList<>())
                    .inKindDonations(new ArrayList<>())
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
