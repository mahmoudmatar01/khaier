package com.example.khaier.mapper;

import com.example.khaier.dto.request.CommentRequestDto;
import com.example.khaier.entity.Comment;
import com.example.khaier.entity.Post;
import com.example.khaier.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CommentRequestDtoToCommentMapper implements Function<CommentRequestDto, Comment> {

    private final PostRepository postRepository;
    @Override
    public Comment apply(CommentRequestDto commentRequestDto) {
        Post post=postRepository.findById(commentRequestDto.postId()).orElseThrow(
                ()->new NotFoundException("Post with id : "+commentRequestDto.postId()+" not found!")
        );
        return Comment.builder()
                .content(commentRequestDto.commentContent())
                .date(LocalDateTime.now())
                .post(post)
                .replies(new ArrayList<>())
                .build();
    }
}
