package com.example.khaier.service.Impl;

import com.example.khaier.entity.UserImage;
import com.example.khaier.repository.UserImageRepository;
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
public class UserImageServiceImpl implements ImageService<UserImage> {
    private final UserImageRepository userImageRepository;
    private final ImageUtils imageUtils;

    @Override
    public UserImage uploadImage(MultipartFile file) throws IOException {
        String uniqueImageTitle=generateUniqueImageTitle(file.getOriginalFilename());
        UserImage userImage =createImage(uniqueImageTitle,file);
        return userImageRepository.save(userImage);
    }

    @Override
    public byte[] downloadImage(String title){
        UserImage userImage = userImageRepository.findByTitle(title).orElseThrow(()->
                new NotFoundException("Image with title:" + title + " is not found"));
        return decompressImage(userImage.getData());
    }

    @Override
    public String generateUrl(String title){
        return  imageUtils.generateImagePath("auth/image",title);
    }

    private UserImage createImage(String uniqueImageTitle, MultipartFile file) throws IOException {
        return UserImage.builder()
                .title(uniqueImageTitle)
                .type(file.getContentType())
                .data(compressImage(file.getBytes()))
                .url(generateUrl(uniqueImageTitle))
                .build();
    }

}
