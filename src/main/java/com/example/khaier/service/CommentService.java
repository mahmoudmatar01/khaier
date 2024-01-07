package com.example.khaier.service;

import com.example.khaier.dto.request.CommentRequestDto;
import com.example.khaier.dto.response.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto addComment(CommentRequestDto commentRequestDto, Long userId);
    CommentResponseDto getById(Long commentId);

    List<CommentResponseDto> getCommentsByPostId(Long postId);
}
