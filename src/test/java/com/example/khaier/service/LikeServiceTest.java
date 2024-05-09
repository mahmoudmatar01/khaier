package com.example.khaier.service;

import com.example.khaier.entity.Like;
import com.example.khaier.entity.Post;
import com.example.khaier.entity.User;
import com.example.khaier.helper.PostHelper;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.LikeToLikeResponseDtoMapper;
import com.example.khaier.repository.LikeRepository;
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

import static org.mockito.Mockito.when;

@SpringBootTest
public class LikeServiceTest {
    @MockBean
    LikeRepository likeRepository;
    @Autowired
    LikeService likeService;
    @Autowired
    LikeToLikeResponseDtoMapper toLikeResponseDtoMapper;
    @MockBean
    SecurityContext securityContext;
    @MockBean
    Authentication authentication;
    Post postMock = Mockito.mock(Post.class);
    User userMock = Mockito.mock(User.class);
    PostHelper postHelper = Mockito.mock(PostHelper.class);
    UserHelper userHelper = Mockito.mock(UserHelper.class);

    @BeforeEach
    void init(){
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userMock);
        when(userMock.getUserId()).thenReturn(1L);
        Mockito.when(userHelper.findUserByIdOrThrowNotFoundException(1L)).thenReturn(userMock);
        when(postHelper.findPostByIdOrThrowNotFound(1L)).thenReturn(postMock);
    }

    @Test
    void whenAddLike_thenReturnLike(){
        Like like = Like.builder()
                .likeId(1L).isLiked(true)
                .post(postMock).user(userMock)
                .date(LocalDateTime.now())
                .build();
        when(likeRepository.save(Mockito.any(Like.class))).thenReturn(like);
        Assertions.assertEquals(likeService.addOrRemoveLike(1L),
                toLikeResponseDtoMapper.apply(like));
    }

    @Test
    void whenRemoveLike_thenReturnLike(){
        Like existingLike = Like.builder()
                .likeId(1L).isLiked(true)
                .date(LocalDateTime.now())
                .user(userMock).post(postMock).build();
        when(likeRepository.findByPostPostIdAndUserUserId(1L, 1L))
                .thenReturn(existingLike);
        Assertions.assertEquals(likeService.addOrRemoveLike( 1L),
                toLikeResponseDtoMapper.apply(existingLike));
        Mockito.verify(likeRepository).delete(existingLike); //Test delete method was fired
    }

    @Test
    void whenFindLikesByPostId_thenReturnLikesList(){
        when(postHelper.checkExistenceOfPostById(1L)).thenReturn(true);
        Like like1 = Like.builder()
                .post(postMock).user(userMock)
                .isLiked(true).likeId(1L)
                .date(LocalDateTime.now()).build();
        Like like2 = Like.builder()
                .post(postMock).user(userMock)
                .isLiked(true).likeId(2L)
                .date(LocalDateTime.of(2024, 1, 20, 5, 23)).build();
        List<Like> postLikes = List.of(like1, like2);
        when(likeRepository.findAllByPostPostId(1L)).thenReturn(postLikes);
        Assertions.assertEquals(likeService.findLikesByPostId(1L),
                postLikes.stream().map(toLikeResponseDtoMapper).toList());
    }
}