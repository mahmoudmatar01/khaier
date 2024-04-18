package com.example.khaier.service;

import com.example.khaier.dto.request.CommentRequestDto;
import com.example.khaier.entity.Comment;
import com.example.khaier.entity.Post;
import com.example.khaier.entity.User;
import com.example.khaier.helper.PostHelper;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.CommentRequestDtoToCommentMapper;
import com.example.khaier.mapper.CommentToCommentResponseDtoMapper;
import com.example.khaier.repository.CommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CommentServiceTest {
    @MockBean
    CommentRepository commentRepository;
    @Autowired
    CommentService commentService;
    @Autowired
    CommentRequestDtoToCommentMapper toCommentMapper;
    @Autowired
    CommentToCommentResponseDtoMapper toCommentResponseDtoMapper;
    final Post postMock = Mockito.mock(Post.class);
    final User userMock = Mockito.mock(User.class);
    final PostHelper postHelper = Mockito.mock(PostHelper.class);
    final UserHelper userHelper = Mockito.mock(UserHelper.class);


    @BeforeEach
    void init(){
        when(userHelper.findUserByIdOrThrowNotFoundException(1L)).thenReturn(userMock);
        when(postHelper.findPostByIdOrThrowNotFound(1L)).thenReturn(postMock);
    }

    @Test
    void whenCreateComment_thenReturnComment(){
        CommentRequestDto commentRequestDto = CommentRequestDto.builder()
                .commentContent("comment content")
                .postId(1L).build();
        Comment actualComment = toCommentMapper.apply(commentRequestDto);
        actualComment.setUser(userMock);
        //Repository Mock
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(actualComment);

        //Test the Service
        Assertions.assertThat(commentService.addComment(commentRequestDto, 1L))
                .isEqualTo(toCommentResponseDtoMapper.apply(actualComment));
    }

    @Test
    void whenFindCommentById_thenReturnComment(){
        Comment actualComment = Comment.builder()
                .commentId(1L).content("comment content")
                .date(LocalDateTime.now())
                .post(postMock).user(userMock)
                .replies(new ArrayList<>()).build();
        when(commentRepository.findById(1L)).thenReturn(Optional.of(actualComment));

        Assertions.assertThat(commentService.getById(1L))
                .isEqualTo(toCommentResponseDtoMapper.apply(actualComment));
    }

    @Test
    void whenGetCommentsByPostId_thenReturnCommentsList(){
        Comment comment1 = Comment.builder()
                .commentId(1L).content("comment content")
                .date(LocalDateTime.now())
                .post(postMock).user(userMock)
                .replies(new ArrayList<>()).build();
        Comment comment2 = Comment.builder()
                .commentId(2L).content("comment content")
                .date(LocalDateTime.now())
                .post(postMock).user(userMock)
                .replies(new ArrayList<>()).build();
        List<Comment> comments = List.of(comment1, comment2);
        when(commentRepository.findByPost_PostId(1L)).thenReturn(comments);

        Assertions.assertThat(commentService.getCommentsByPostId(1L))
                .isEqualTo(comments.stream().map(toCommentResponseDtoMapper).toList());
    }
}
