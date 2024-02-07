package com.example.khaier.mapper;

import com.example.khaier.dto.request.CharityCategoryRequestDto;
import com.example.khaier.entity.CharitableOrganization;
import com.example.khaier.entity.DonationCategories;
import com.example.khaier.helper.CharityOrgHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CharityCategoryRequestDtoToCharityCategoryMapper implements Function<CharityCategoryRequestDto, DonationCategories> {
    private final CharityOrgHelper charityOrgHelper;
    @Override
    public DonationCategories apply(CharityCategoryRequestDto charityCategoryRequestDto) {
        CharitableOrganization charitableOrganization=charityOrgHelper.findCharityByIdOrThrowNotFound(charityCategoryRequestDto.charityId());
        return DonationCategories.builder()
                .categoryTitle(charityCategoryRequestDto.title())
                .charitableOrganization(charitableOrganization)
                .donationCases(new ArrayList<>())
                .build();
    }
}
