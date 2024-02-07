package com.example.khaier.mapper;

import com.example.khaier.dto.response.CharityCategoryResponseDto;
import com.example.khaier.entity.DonationCategories;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CharityCategoryToCharityCategoryResponseDtoMapper implements Function<DonationCategories, CharityCategoryResponseDto> {
    private final DonationCaseToCaseResponseMapper toCaseResponseMapper;
    @Override
    public CharityCategoryResponseDto apply(DonationCategories donationCategories) {
        return CharityCategoryResponseDto.builder()
                .categoryId(donationCategories.getCategoryId())
                .categoryTitle(donationCategories.getCategoryTitle())
                .caseList(donationCategories.getDonationCases().stream().map(toCaseResponseMapper).toList())
                .build();
    }
}
