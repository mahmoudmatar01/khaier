package com.example.khaier.controller;

import com.example.khaier.dto.request.PostRequestDto;
import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.Impl.PostImageService;
import com.example.khaier.service.Impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;
    private final PostImageService postImageService;
    private final SuccessResponseFactory200 responseFactory;
    @GetMapping("/{userId}")
    public ResponseEntity<?> getPosts(@PathVariable Long userId){
        List<PostResponseDto> response = postService.getAllPosts(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Posts returned successfully "));
    }
    @GetMapping
    public ResponseEntity<?> findPostById(@RequestParam Long userId,@RequestParam Long postId){
        PostResponseDto response = postService.getPostById(postId,userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Post returned successfully "));
    }

    @PostMapping(value = "/{userId}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> savePost(@ModelAttribute PostRequestDto postRequestDto,@PathVariable Long userId){
        PostResponseDto response = postService.addNewPost(postRequestDto,userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Posts saved successfully "));
    }

    @GetMapping(value = "/image/{title}", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getImage(@PathVariable String title){
        byte[] imageData = postImageService.downloadImage(title);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
