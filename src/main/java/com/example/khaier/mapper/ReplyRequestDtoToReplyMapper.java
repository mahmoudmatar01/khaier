package com.example.khaier.mapper;

import com.example.khaier.dto.request.ReplyRequestDto;
import com.example.khaier.entity.Comment;
import com.example.khaier.entity.Reply;
import com.example.khaier.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ReplyRequestDtoToReplyMapper implements Function<ReplyRequestDto, Reply> {

    private final CommentRepository commentRepository;
    @Override
    public Reply apply(ReplyRequestDto replyRequestDto) {
        Comment comment=commentRepository.findById(replyRequestDto.commentId()).orElseThrow(
                ()-> new NotFoundException("Comment with id : "+replyRequestDto.commentId()+" not found")
        );
        return Reply.builder()
                .date(LocalDateTime.now())
                .content(replyRequestDto.content())
                .comment(comment)
                .build();
    }
}
