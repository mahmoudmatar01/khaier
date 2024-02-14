package com.example.khaier.mapper;

import com.example.khaier.dto.response.ReplyResponseDto;
import com.example.khaier.entity.Reply;
import com.example.khaier.helper.TimeSinceFormatterHelper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ReplyToReplyResponseDtoMapper implements Function<Reply, ReplyResponseDto> {
    private final TimeSinceFormatterHelper timeSinceFormatterHelper=TimeSinceFormatterHelper.getInstance();
    @Override
    public ReplyResponseDto apply(Reply reply) {
        return ReplyResponseDto
                .builder()
                .id(reply.getReplyId())
                .content(reply.getContent())
                .publishDate(reply.getDate())
                .createdSince(timeSinceFormatterHelper.formatTimeSince(reply.getDate()))
                .commentId(reply.getComment().getCommentId())
                .userId(reply.getUser().getUserId())
                .userName(reply.getUser().getUsername())
                .userImageUrl(reply.getUser().getUserImageUrl())
                .build();
    }
}
