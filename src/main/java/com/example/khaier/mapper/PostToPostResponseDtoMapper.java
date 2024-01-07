package com.example.khaier.mapper;

import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.entity.post.Post;
import com.example.khaier.entity.post.PostImage;
import com.example.khaier.helper.LikeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostToPostResponseDtoMapper implements Function<Post, PostResponseDto> {

    private final CommentToCommentResponseDtoMapper toCommentResponseDtoMapper;
    private final LikeToLikeResponseDtoMapper toLikeResponseDtoMapper;
    private final LikeHelper likeHelper;
    @Override
    public PostResponseDto apply(Post post) {
        return apply(post,null);
    }
    public PostResponseDto apply(Post post, Long userId) {
        boolean isLiked = (userId != null) && likeHelper.isUserLikedPost(post, userId);
        return PostResponseDto
                .builder()
                .id(post.getPostId())
                .content(post.getPostContent())
                .dateTime(post.getDate())
                .imagesUrl(post.getImages()!=null?post.getImages().stream().map(PostImage::getUrl).toList():new ArrayList<>())
                .userId(post.getUser().getUserId())
                .isUserLike(isLiked)
                .userName(post.getUser().getUsername())
                .userImage(post.getUser().getUserImageUrl())
                .comments(post.getComments().stream().map(toCommentResponseDtoMapper::apply).collect(Collectors.toList()))
                .likes(post.getLikes().stream().map(toLikeResponseDtoMapper::apply).collect(Collectors.toList()))
                .build();
    }
}
