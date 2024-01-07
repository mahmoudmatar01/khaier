package com.example.khaier.service;

import com.example.khaier.dto.request.ReplyRequestDto;
import com.example.khaier.dto.response.ReplyResponseDto;

import java.util.List;

public interface ReplyService {
    ReplyResponseDto addReply(ReplyRequestDto requestDto, Long userId);
    List<ReplyResponseDto> getByCommentId(Long commentId);
}
