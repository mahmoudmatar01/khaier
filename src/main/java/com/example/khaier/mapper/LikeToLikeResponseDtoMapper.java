package com.example.khaier.mapper;

import com.example.khaier.dto.response.LikeResponseDto;
import com.example.khaier.entity.Like;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class LikeToLikeResponseDtoMapper implements Function<Like, LikeResponseDto> {
    @Override
    public LikeResponseDto apply(Like like) {
        return LikeResponseDto
                .builder()
                .id(like.getLikeId())
                .isLiked(like.isLiked())
                .date(like.getDate())
                .postId(like.getPost().getPostId())
                .userId(like.getUser().getUserId())
                .userName(like.getUser().getUsername())
                .userImageUrl(like.getUser().getUserImageUrl())
                .build();
    }
}
