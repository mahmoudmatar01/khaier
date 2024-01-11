package com.example.khaier.service;

import com.example.khaier.dto.request.PostRequestDto;
import com.example.khaier.dto.response.PostResponseDto;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostService {
    List<PostResponseDto> getAllPosts(Long userId, Pageable pageable);
    PostResponseDto addNewPost(PostRequestDto postDto, Long userId);
    PostResponseDto getPostById(Long postId,Long userId);

}
