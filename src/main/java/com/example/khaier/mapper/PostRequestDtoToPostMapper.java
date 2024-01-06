package com.example.khaier.mapper;

import com.example.khaier.dto.request.PostRequestDto;
import com.example.khaier.entity.Post;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Function;

@Component
public class PostRequestDtoToPostMapper implements Function<PostRequestDto, Post> {
    @Override
    public Post apply(PostRequestDto postRequestDto) {
        return Post.builder()
                .postContent(postRequestDto.postContent())
                .date(LocalDateTime.now())
                .comments(new ArrayList<>())
                .likes(new ArrayList<>())
                .build();
    }
}
