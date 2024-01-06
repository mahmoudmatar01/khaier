package com.example.khaier.mapper;

import com.example.khaier.dto.response.CommentResponseDto;
import com.example.khaier.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommentToCommentResponseDtoMapper implements Function<Comment, CommentResponseDto> {

    private final ReplyToReplyResponseDtoMapper toReplyResponseDtoMapper;
    @Override
    public CommentResponseDto apply(Comment comment) {
        return CommentResponseDto
                .builder()
                .id(comment.getCommentId())
                .commentContent(comment.getContent())
                .publishDate(comment.getDate())
                .postId(comment.getPost().getPostId())
                .userId(comment.getUser().getUserId())
                .userName(comment.getUser().getUsername())
                .userImageUrl(comment.getUser().getUserImageUrl())
                .replies(comment.getReplies().stream().map(toReplyResponseDtoMapper::apply).collect(Collectors.toList()))
                .build();
    }
}
