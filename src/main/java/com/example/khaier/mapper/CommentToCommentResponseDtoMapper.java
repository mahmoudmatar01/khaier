package com.example.khaier.mapper;

import com.example.khaier.dto.response.CommentResponseDto;
import com.example.khaier.entity.Comment;
import com.example.khaier.helper.TimeSinceFormatterHelper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CommentToCommentResponseDtoMapper implements Function<Comment, CommentResponseDto> {

    private final TimeSinceFormatterHelper timeSinceFormatterHelper=TimeSinceFormatterHelper.getInstance();
    @Override
    public CommentResponseDto apply(Comment comment) {
        return CommentResponseDto
                .builder()
                .id(comment.getCommentId())
                .commentContent(comment.getContent())
                .publishDate(comment.getDate())
                .createdSince(timeSinceFormatterHelper.formatTimeSince(comment.getDate()))
                .postId(comment.getPost().getPostId())
                .userId(comment.getUser().getUserId())
                .userName(comment.getUser().getUsername())
                .userImageUrl(comment.getUser().getUserImageUrl())
                .numberOfReplies(comment.getReplies().size())
                .build();
    }
}
