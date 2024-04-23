package com.example.khaier.dto.response;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostResponseDto (
        Long id,
        String content,
        LocalDateTime dateTime,
        String createdSince,
        List<String>imagesUrl,
        Long userId,
        boolean isUserLike,
        String userName,
        String userImage,
        long numberOfLikes,
        long numberOfComments

){
}
