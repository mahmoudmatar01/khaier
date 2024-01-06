package com.example.khaier.dto.response;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record LikeResponseDto(
        Long id,
        boolean isLiked,
        LocalDateTime date,
        Long postId,
        Long userId,
        String userName,
        String userImageUrl

) {
}
