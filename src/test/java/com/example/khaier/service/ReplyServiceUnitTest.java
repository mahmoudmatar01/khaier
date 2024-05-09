package com.example.khaier.service;

import com.example.khaier.dto.request.ReplyRequestDto;
import com.example.khaier.entity.Comment;
import com.example.khaier.entity.Reply;
import com.example.khaier.entity.User;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.ReplyRequestDtoToReplyMapper;
import com.example.khaier.mapper.ReplyToReplyResponseDtoMapper;
import com.example.khaier.repository.CommentRepository;
import com.example.khaier.repository.ReplyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ReplyServiceUnitTest {
    @MockBean
    ReplyRepository replyRepository;
    @Autowired
    ReplyService replyService;
    @Autowired
    ReplyRequestDtoToReplyMapper toReplyMapper;
    @Autowired
    ReplyToReplyResponseDtoMapper toReplyResponseDtoMapper;
    @MockBean
    User userMock;
    @MockBean
    UserHelper userHelper;
    @MockBean
    SecurityContext securityContext;
    @MockBean
    Authentication authentication;
    @MockBean
    Comment commentMock;
    @MockBean
    CommentRepository commentRepository;

    @BeforeEach
    void init(){
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userMock);
        when(userHelper.findUserByIdOrThrowNotFoundException(1L)).thenReturn(userMock);
        when(commentRepository.findById(1L)).thenReturn(Optional.of(commentMock));
    }

    @Test
    void whenAddReply_thenReturnReply(){
        ReplyRequestDto replyRequestDto = ReplyRequestDto.builder()
                .content("reply content")
                .commentId(1L).build();
        Reply actualReply = toReplyMapper.apply(replyRequestDto);
        actualReply.setUser(userMock);
        when(replyRepository.save(Mockito.any(Reply.class))).thenReturn(actualReply);
        Assertions.assertEquals(replyService.addReply(replyRequestDto),
                toReplyResponseDtoMapper.apply(actualReply));
    }

    @Test
    void whenFindRepliesByCommentId_thenReturnReplies() {
        Reply reply1 = Reply.builder()
                .comment(commentMock).content("reply1 content")
                .user(userMock).date(LocalDateTime.now())
                .replyId(1L).build();
        Reply reply2 = Reply.builder()
                .comment(commentMock).content("reply2 content")
                .user(userMock).date(LocalDateTime.now())
                .replyId(2L).build();
        List<Reply> replyList = List.of(reply1, reply2);
        when(replyRepository.findByComment_CommentId(1L)).thenReturn(replyList);

        //Assertion Test
        Assertions.assertEquals(replyService.getByCommentId(1L),
                replyList.stream().map(toReplyResponseDtoMapper).toList());
    }
}