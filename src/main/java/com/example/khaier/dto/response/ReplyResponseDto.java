package com.example.khaier.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReplyResponseDto(
        Long id,
        String content,
        LocalDateTime publishDate,
        String createdSince,
        Long commentId,
        Long userId,
        String userName,
        String userImageUrl

) {
}
