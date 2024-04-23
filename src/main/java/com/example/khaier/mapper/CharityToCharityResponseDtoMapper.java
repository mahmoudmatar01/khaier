package com.example.khaier.mapper;

import com.example.khaier.dto.response.CharityResponseDto;
import com.example.khaier.entity.CharitableOrganization;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CharityToCharityResponseDtoMapper implements Function<CharitableOrganization,CharityResponseDto> {

    @Override
    public CharityResponseDto apply(CharitableOrganization charitableOrganization) {
        return CharityResponseDto
                .builder()
                .charityId(charitableOrganization.getOrgId())
                .charityName(charitableOrganization.getOrgName())
                .charityDescription(charitableOrganization.getDescription())
                .charityLocation(charitableOrganization.getLocation())
                .imageUrl(charitableOrganization.getCharitableOrgImage().getUrl())
                .facebookPageUrl(charitableOrganization.getFacebookUrl())
                .instagramPageUrl(charitableOrganization.getInstagramUrl())
                .whatsappNumber(charitableOrganization.getOrgWhatsappNumber())
                .phoneNumber(charitableOrganization.getOrgPhoneNumber())
                .build();
    }
}
