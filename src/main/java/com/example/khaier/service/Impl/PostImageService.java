package com.example.khaier.service.Impl;

import com.example.khaier.entity.PostImage;
import com.example.khaier.repository.PostImageRepository;
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
public class PostImageService implements ImageService<PostImage> {

    private final PostImageRepository postImageRepository;
    private final ImageUtils imageUtils;

    @Override
    public PostImage uploadImage(MultipartFile file) throws IOException {
        String uniqueImageTitle=generateUniqueImageTitle(file.getOriginalFilename());
        PostImage postImage =createImage(uniqueImageTitle,file);
        postImage= postImageRepository.save(postImage);
        return postImage;
    }
    @Override
    public byte[] downloadImage(String title){
        PostImage postImage = postImageRepository.findByTitle(title).orElseThrow(()->
                new NotFoundException("Image with title:" + title + " is not found"));
        return decompressImage(postImage.getData());
    }
    @Override
    public String generateUrl(String title){
        return  imageUtils.generateImagePath("post/image",title);

    }
    private PostImage createImage(String uniqueImageTitle, MultipartFile file) throws IOException {
        PostImage postImage=new PostImage();
        postImage.setData(compressImage(file.getBytes()));
        postImage.setTitle(uniqueImageTitle);
        postImage.setUrl(generateUrl(uniqueImageTitle));
        postImage.setType(file.getContentType());
        return postImage;
    }
}
