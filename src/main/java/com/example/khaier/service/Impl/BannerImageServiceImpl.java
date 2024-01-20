package com.example.khaier.service.Impl;

import com.example.khaier.entity.BannerImage;
import com.example.khaier.repository.BannerImageRepository;
import com.example.khaier.service.ImageService;
import com.example.khaier.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;

import static com.example.khaier.utils.ImageUtils.*;

@Service
@RequiredArgsConstructor
public class BannerImageServiceImpl implements ImageService<BannerImage> {
    private final BannerImageRepository bannerImageRepository;
    private final ImageUtils imageUtils;

    @Override
    public BannerImage uploadImage(MultipartFile file) throws IOException {
        String uniqueImageTitle=generateUniqueImageTitle(file.getOriginalFilename());
        BannerImage bannerImage = createImage(uniqueImageTitle, file);
        return bannerImageRepository.save(bannerImage);
    }
    @Override
    public byte[] downloadImage(String title){
        BannerImage bannerImage = bannerImageRepository.findByTitle(title).orElseThrow(()->
                new NotFoundException("Image with title:" + title + " is not found"));
        return decompressImage(bannerImage.getData());
    }
    @Override
    public String generateUrl(String title){
        return  imageUtils.generateImagePath("banner/image",title);
    }

    private BannerImage createImage(String uniqueImageTitle, MultipartFile file) throws IOException {
        return BannerImage.builder()
                .title(uniqueImageTitle)
                .type(file.getContentType())
                .data(compressImage(file.getBytes()))
                .url(generateUrl(uniqueImageTitle))
                .build();
    }
}
