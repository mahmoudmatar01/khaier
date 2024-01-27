package com.example.khaier.service.Impl;

import com.example.khaier.entity.CharitableOrgImage;
import com.example.khaier.repository.CharityImageRepository;
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
public class CharityImageService implements ImageService<CharitableOrgImage> {

    private final CharityImageRepository charityImageRepository;
    private final ImageUtils imageUtils;

    @Override
    public CharitableOrgImage uploadImage(MultipartFile file) throws IOException {
        String uniqueImageTitle=generateUniqueImageTitle(file.getOriginalFilename());
        CharitableOrgImage charitableOrgImage =createImage(uniqueImageTitle,file);
        charitableOrgImage= charityImageRepository.save(charitableOrgImage);
        return charitableOrgImage;
    }

    @Override
    public byte[] downloadImage(String title){
        CharitableOrgImage postImage = charityImageRepository.findByTitle(title).orElseThrow(()->
                new NotFoundException("Image with title:" + title + " is not found"));
        return decompressImage(postImage.getData());
    }
    @Override
    public String generateUrl(String title){
        return  imageUtils.generateImagePath("charity/image",title);

    }

    private CharitableOrgImage createImage(String uniqueImageTitle, MultipartFile file) throws IOException {
        CharitableOrgImage charitableOrgImage=new CharitableOrgImage();
        charitableOrgImage.setData(compressImage(file.getBytes()));
        charitableOrgImage.setTitle(uniqueImageTitle);
        charitableOrgImage.setUrl(generateUrl(uniqueImageTitle));
        charitableOrgImage.setType(file.getContentType());
        return charitableOrgImage;
    }
}
