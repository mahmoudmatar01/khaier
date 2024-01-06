package com.example.khaier.dto.response;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostResponseDto (
        Long id,
        String content,
        LocalDateTime dateTime,
        Long userId,
        String userName,
        String userImage,
        List<LikeResponseDto>likes,
        List<CommentResponseDto>comments
){
}
