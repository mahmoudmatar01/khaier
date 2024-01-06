package com.example.khaier.dto.request;

import lombok.Builder;

@Builder
public record CommentRequestDto(
        String commentContent,
        Long postId
) {
}
