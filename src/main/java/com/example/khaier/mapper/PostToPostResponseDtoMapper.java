package com.example.khaier.mapper;

import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostToPostResponseDtoMapper implements Function<Post, PostResponseDto> {

    private final CommentToCommentResponseDtoMapper toCommentResponseDtoMapper;
    private final LikeToLikeResponseDtoMapper toLikeResponseDtoMapper;
    @Override
    public PostResponseDto apply(Post post) {

        return PostResponseDto
                .builder()
                .id(post.getPostId())
                .content(post.getPostContent())
                .dateTime(post.getDate())
                .userId(post.getUser().getUserId())
                .userName(post.getUser().getUsername())
                .userImage(post.getUser().getUserImageUrl())
                .comments(post.getComments().stream().map(toCommentResponseDtoMapper::apply).collect(Collectors.toList()))
                .likes(post.getLikes().stream().map(toLikeResponseDtoMapper::apply).collect(Collectors.toList()))
                .build();
    }
}
