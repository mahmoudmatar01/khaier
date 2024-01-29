package com.example.khaier.dto.response;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CommentResponseDto(
        Long id,
        String commentContent,
        LocalDateTime publishDate,
        String createdSince,
        Long postId,
        Long userId,
        String userName,
        String userImageUrl,
        List<ReplyResponseDto>replies

) {
}
