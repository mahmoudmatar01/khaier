package com.example.khaier.service.Impl;
import com.example.khaier.entity.CampaignImage;
import com.example.khaier.repository.CampaignImageRepository;
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
public class CampaignImageService implements ImageService<CampaignImage> {
    private final ImageUtils imageUtils;
    private final CampaignImageRepository repository;

    @Override
    public CampaignImage uploadImage(MultipartFile file) throws IOException {
        String uniqueImageTitle=generateUniqueImageTitle(file.getOriginalFilename());
        CampaignImage campaignImage = createImage(uniqueImageTitle, file);
        return repository.save(campaignImage);
    }

    @Override
    public byte[] downloadImage(String title) {
        CampaignImage campaignImage = repository.findByTitle(title).orElseThrow(()->
                new NotFoundException("Image with title:" + title + " is not found"));
        return decompressImage(campaignImage.getData());
    }

    @Override
    public String generateUrl(String title) {
        return  imageUtils.generateImagePath("campaign/image",title);
    }
    private CampaignImage createImage(String uniqueImageTitle, MultipartFile file) throws IOException {
        CampaignImage campaignImage= new CampaignImage();
        campaignImage.setData(compressImage(file.getBytes()));
        campaignImage.setTitle(uniqueImageTitle);
        campaignImage.setUrl(generateUrl(uniqueImageTitle));
        campaignImage.setType(file.getContentType());
        return campaignImage;
    }
}
