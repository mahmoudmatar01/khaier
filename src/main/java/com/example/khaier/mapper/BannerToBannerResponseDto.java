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

        return BannerResponseDto
                .builder()
                .Id(banner.getBannerId())
                .title(banner.getTitle())
                .description(banner.getDescription())
                .imageUrl(banner.getImageUrl())
                .build();
    }
}
