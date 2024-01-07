package com.example.khaier.service;

import com.example.khaier.dto.request.PostRequestDto;
import com.example.khaier.dto.response.PostResponseDto;

import java.util.List;

public interface PostService {
    List<PostResponseDto> getAllPosts(Long userId);
    PostResponseDto addNewPost(PostRequestDto postDto, Long userId);
    PostResponseDto getPostById(Long postId,Long userId);

}
