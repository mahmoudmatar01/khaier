package com.example.khaier.mapper;

import com.example.khaier.dto.response.CharityCategoryResponseDto;
import com.example.khaier.entity.DonationCategories;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CharityCategoryToCharityCategoryResponseDtoMapper implements Function<DonationCategories, CharityCategoryResponseDto> {
    @Override
    public CharityCategoryResponseDto apply(DonationCategories donationCategories) {
        return CharityCategoryResponseDto.builder()
                .categoryId(donationCategories.getCategoryId())
                .categoryTitle(donationCategories.getCategoryTitle())
                .build();
    }
}
