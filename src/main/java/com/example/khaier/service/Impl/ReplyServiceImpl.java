package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.ReplyRequestDto;
import com.example.khaier.dto.response.ReplyResponseDto;
import com.example.khaier.entity.Reply;
import com.example.khaier.entity.User;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.ReplyRequestDtoToReplyMapper;
import com.example.khaier.mapper.ReplyToReplyResponseDtoMapper;
import com.example.khaier.repository.CommentRepository;
import com.example.khaier.repository.ReplyRepository;
import com.example.khaier.service.ReplyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyToReplyResponseDtoMapper toReplyResponseDtoMapper;
    private final ReplyRequestDtoToReplyMapper toReplyMapper;
    private final ReplyRepository repository;
    private final CommentRepository commentRepository;
    private final UserHelper userHelper;
    @Override
    public ReplyResponseDto addReply(ReplyRequestDto requestDto, Long userId){
        User user=userHelper.findUserByIdOrThrowNotFoundException(userId);
        Reply reply=toReplyMapper.apply(requestDto);
        reply.setUser(user);
        reply=repository.save(reply);
        reply.getComment().getReplies().add(reply);
        return toReplyResponseDtoMapper.apply(reply);
    }

    @Override
    public List<ReplyResponseDto> getByCommentId(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(
                ()->new NotFoundException("Comment with id : "+commentId+" not found!")
        );
        List<Reply>replies=repository.findByComment_CommentId(commentId);
        return replies.stream().map(toReplyResponseDtoMapper).toList();
    }
}
