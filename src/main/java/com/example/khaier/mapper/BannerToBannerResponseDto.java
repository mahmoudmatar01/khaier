package com.example.khaier.mapper;

import com.example.khaier.dto.response.BannerResponseDto;
import com.example.khaier.entity.banner.Banner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BannerToBannerResponseDto implements Function<Banner, BannerResponseDto>{
    @Override
    public BannerResponseDto apply(Banner banner) {

        return BannerResponseDto.builder()
                .description(banner.getDescription())
                .title(banner.getTitle())
                .imageUrl(banner.getImageUrl())
                .Id(banner.getBannerId()).build();
    }
}
