package com.example.khaier.mapper;

import com.example.khaier.dto.request.CharityRequestDto;
import com.example.khaier.entity.CharitableOrgImage;
import com.example.khaier.entity.CharitableOrganization;
import com.example.khaier.service.Impl.CharityImageService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CharityRequestDtoToCharityMapper implements Function<CharityRequestDto, CharitableOrganization> {
    private final CharityImageService charityImageService;
    @Override
    public CharitableOrganization apply(CharityRequestDto charityRequestDto) {
        return CharitableOrganization
                .builder()
                .orgName(charityRequestDto.charityName())
                .description(charityRequestDto.charityDescription())
                .charitableOrgImage(uploadImage(charityRequestDto.image()))
                .facebookUrl(charityRequestDto.facebookPageUrl())
                .location(charityRequestDto.charityLocation())
                .instagramUrl(charityRequestDto.instagramPageUrl())
                .orgPhoneNumber(charityRequestDto.phoneNumber())
                .orgWhatsappNumber(charityRequestDto.whatsappNumber())
                .donationCampaigns(new ArrayList<>())
                .donationCategories(new ArrayList<>())
                .inKindDonations(new ArrayList<>())
                .build();

    }

    private CharitableOrgImage uploadImage(MultipartFile image){
        try {
            return charityImageService.uploadImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
