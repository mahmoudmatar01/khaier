package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.CommentRequestDto;
import com.example.khaier.dto.response.CommentResponseDto;
import com.example.khaier.entity.Comment;
import com.example.khaier.entity.user.User;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.CommentRequestDtoToCommentMapper;
import com.example.khaier.mapper.CommentToCommentResponseDtoMapper;
import com.example.khaier.repository.CommentRepository;
import com.example.khaier.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl {

    private final CommentRepository commentRepository;
    private final UserHelper userHelper;
    private final CommentRequestDtoToCommentMapper toCommentMapper;
    private final PostRepository postRepository;
    private final CommentToCommentResponseDtoMapper toCommentResponseDtoMapper;

    public CommentResponseDto addComment(CommentRequestDto commentRequestDto,Long userId){
        User user=userHelper.checkUserIsExistOrThrowException(userId);
        Comment comment=toCommentMapper.apply(commentRequestDto);
        comment.setUser(user);
        comment=commentRepository.save(comment);
        postRepository.findById(commentRequestDto.postId()).get().getComments().add(comment);
        return  toCommentResponseDtoMapper.apply(comment);
    }

    public CommentResponseDto getById(Long commentId){
        Comment comment=commentRepository.findById(commentId).orElseThrow(
                ()->new NotFoundException("Comment with id : "+commentId+" not found!")
        );
        return toCommentResponseDtoMapper.apply(comment);
    }
}
