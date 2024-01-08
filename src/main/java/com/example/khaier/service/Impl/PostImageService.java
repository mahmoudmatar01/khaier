package com.example.khaier.service.Impl;

import com.example.khaier.entity.post.PostImage;
import com.example.khaier.repository.post.PostImageRepository;
import com.example.khaier.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;

import static com.example.khaier.utils.ImageUtils.*;

@Service
@RequiredArgsConstructor
public class PostImageService {

    private final PostImageRepository postImageRepository;
    private final ImageUtils imageUtils;
    public PostImage uploadImage(MultipartFile file) throws IOException {
        String uniqueImageTitle=generateUniqueImageTitle(file.getOriginalFilename());
        PostImage postImage =PostImage.builder()
                .title(uniqueImageTitle)
                .type(file.getContentType())
                .data(compressImage(file.getBytes()))
                .url(generateUrl(uniqueImageTitle))
                .build();
        postImage= postImageRepository.save(postImage);
        return postImage;
    }
    public byte[] downloadImage(String title){
        PostImage postImage = postImageRepository.findByTitle(title).orElseThrow(()->
                new NotFoundException("Image with title:" + title + " is not found"));
        return decompressImage(postImage.getData());
    }
    public String generateUrl(String title){
        return  imageUtils.generateImagePath("post/image",title);

    }
}
