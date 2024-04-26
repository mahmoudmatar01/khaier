package com.example.khaier.service;

import com.example.khaier.dto.request.PostRequestDto;
import com.example.khaier.dto.response.PostResponseDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostService {
    List<PostResponseDto> getAllPosts (Pageable pageable);
    PostResponseDto addNewPost(PostRequestDto postDto);
    PostResponseDto getPostById(Long postId);

}
