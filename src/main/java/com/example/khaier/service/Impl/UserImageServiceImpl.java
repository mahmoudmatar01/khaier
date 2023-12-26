package com.example.khaier.service.Impl;

import com.example.khaier.entity.user.User;
import com.example.khaier.entity.user.UserImage;
import com.example.khaier.repository.user.UserImageRepository;
import com.example.khaier.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;

import static com.example.khaier.utils.ImageUtils.*;

@Service
@RequiredArgsConstructor
public class UserImageServiceImpl {
    private final UserImageRepository userImageRepository;
    public UserImage uploadImage(MultipartFile file) throws IOException {
        String uniqueImageTitle=generateUniqueImageTitle(file.getOriginalFilename());
        UserImage userImage =UserImage.builder()
                .title(uniqueImageTitle)
                .type(file.getContentType())
                .data(compressImage(file.getBytes()))
                .url(generateUrl(uniqueImageTitle))
                .build();
        return userImageRepository.save(userImage);
    }
    public byte[] downloadImage(String title){
        UserImage userImage = userImageRepository.findByTitle(title).orElseThrow(()->
                new NotFoundException("Image with title:" + title + " is not found"));
        return decompressImage(userImage.getData());
    }
    public String generateUrl(String title){
        return "localhost:5920/api/v1/auth/image/"+ title ;
    }

}
