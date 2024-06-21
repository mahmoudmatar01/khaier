package com.example.khaier.controller;

import com.example.khaier.dto.request.PostRequestDto;
import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.Impl.PostImageService;
import com.example.khaier.service.Impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/${api.version}/post")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;
    private final PostImageService postImageService;
    private final SuccessResponseFactory200 responseFactory;
    @Value("${page.size}")
    private int pageSize;

    @GetMapping
    public ResponseEntity<?> getPosts(@RequestParam(defaultValue = "0") int page){
        Pageable pageable = PageRequest.of(page, pageSize,Sort.by(Sort.Direction.DESC, "date"));
        List<PostResponseDto> response = postService.getAllPosts(pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Posts returned successfully "));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> findPostById(@PathVariable Long postId){
        PostResponseDto response = postService.getPostById(postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Post returned successfully "));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> savePost(@ModelAttribute PostRequestDto postRequestDto){
        PostResponseDto response = postService.addNewPost(postRequestDto);
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
