package com.example.khaier.mapper;

import com.example.khaier.dto.request.BannerRequestDto;
import com.example.khaier.entity.Banner;
import com.example.khaier.entity.BannerImage;
import com.example.khaier.service.Impl.BannerImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BannerRequestDtoToBanner implements Function<BannerRequestDto, Banner> {
    private final BannerImageServiceImpl bannerImageService;

    @Override
    public Banner apply(BannerRequestDto bannerRequestDto) {
        BannerImage bannerImage=uploadImage(bannerRequestDto.image());
        return Banner.builder()
                .bannerImage(bannerImage)
                .description(bannerRequestDto.description())
                .imageUrl(bannerImage.getUrl())
                .title(bannerRequestDto.title())
                .build();
    }

    private BannerImage uploadImage(MultipartFile image){
        try{
            return bannerImageService.uploadImage(image);
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
