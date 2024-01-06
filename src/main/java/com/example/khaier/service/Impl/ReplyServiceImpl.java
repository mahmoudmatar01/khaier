package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.ReplyRequestDto;
import com.example.khaier.dto.response.ReplyResponseDto;
import com.example.khaier.entity.Reply;
import com.example.khaier.entity.user.User;
import com.example.khaier.mapper.ReplyRequestDtoToReplyMapper;
import com.example.khaier.mapper.ReplyToReplyResponseDtoMapper;
import com.example.khaier.repository.CommentRepository;
import com.example.khaier.repository.ReplyRepository;
import com.example.khaier.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl {

    private final ReplyToReplyResponseDtoMapper toReplyResponseDtoMapper;
    private final ReplyRequestDtoToReplyMapper toReplyMapper;
    private final UserRepository userRepository;
    private final ReplyRepository repository;

    public ReplyResponseDto addReply(ReplyRequestDto requestDto, Long userId){
        User user=userRepository.findById(userId).orElseThrow(
                ()->new NotFoundException("User with id : "+userId+" not found!")
        );
        Reply reply=toReplyMapper.apply(requestDto);
        reply.setUser(user);
        reply=repository.save(reply);
        reply.getComment().getReplies().add(reply);
        return toReplyResponseDtoMapper.apply(reply);
    }
}
