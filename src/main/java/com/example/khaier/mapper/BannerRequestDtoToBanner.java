package com.example.khaier.mapper;

import com.example.khaier.dto.request.BannerRequestDto;
import com.example.khaier.entity.banner.Banner;
import com.example.khaier.entity.banner.BannerImage;
import com.example.khaier.service.Impl.BannerImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BannerRequestDtoToBanner implements Function<BannerRequestDto, Banner> {
    private final BannerImageServiceImpl bannerImageService;

    @Override
    public Banner apply(BannerRequestDto bannerRequestDto) {
        try{
            BannerImage bannerImage  = bannerImageService.uploadImage(bannerRequestDto.image());

            return Banner.builder()
                    .bannerImage(bannerImage)
                    .description(bannerRequestDto.description())
                    .imageUrl(bannerImage.getUrl())
                    .title(bannerRequestDto.title())
                    .build();
        }
        catch (IOException e){
            throw new RuntimeException("Banner image cannot be uploaded");
        }
    }
}
