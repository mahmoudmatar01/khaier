package com.example.khaier.service;

import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.entity.Bookmark;
import com.example.khaier.entity.Post;
import com.example.khaier.entity.User;
import com.example.khaier.helper.PostHelper;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.PostToPostResponseDtoMapper;
import com.example.khaier.repository.BookmarkRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;


@SpringBootTest
@DisplayName("Bookmark Service Unit Test")
public class BookmarkServiceTest {
    @MockBean
    BookmarkRepository bookmarkRepository;
    @Autowired
    BookmarkService bookmarkService;
    @Autowired
    PostToPostResponseDtoMapper toPostResponseDtoMapper;

    @MockBean
    PostHelper postHelper;
    @MockBean
    UserHelper userHelperMock ;

    User userMock = Mockito.mock(User.class);
    @Test
    void whenAddPostToBookmarks_thenVerified(){
        Post postMock = Mockito.mock(Post.class);
        Bookmark savedBookmark = Mockito.mock(Bookmark.class);
        Mockito.when(userHelperMock.findUserByIdOrThrowNotFoundException(1L)).thenReturn(userMock);
        Mockito.when(postHelper.findPostByIdOrThrowNotFound(1L)).thenReturn(postMock);
        when(bookmarkRepository.save(Mockito.any(Bookmark.class))).thenReturn(savedBookmark);
        bookmarkService.savePostToBookmark(1L, 1L);
        verify(bookmarkRepository).save(Mockito.any(Bookmark.class));
    }

    @Test
    void whenGetBookmarkedPosts_thenReturnListOfPosts(){
        //Mock bookmarked posts
        Post post1 = Mockito.mock(Post.class);
        when(post1.getUser()).thenReturn(userMock);
        when(post1.getDate()).thenReturn(LocalDateTime.now());
        when(postHelper.findPostByIdOrThrowNotFound(1L)).thenReturn(post1);

        Post post2 = Mockito.mock(Post.class);
        when(post2.getUser()).thenReturn(userMock);
        when(post2.getDate()).thenReturn(LocalDateTime.now());
        when(postHelper.findPostByIdOrThrowNotFound(2L)).thenReturn(post2);

        //Create two bookmarks
        Bookmark bookmark1 = Bookmark.builder()
                .bookmarkId(1L).date(LocalDateTime.now())
                .post(post1).user(userMock).build();
        Bookmark bookmark2 = Bookmark.builder()
                .bookmarkId(2L).date(LocalDateTime.now())
                .post(post2).user(userMock).build();

        List<Bookmark> actualBookmarks = List.of(bookmark1, bookmark2);

        //Bookmark Repository Mockup
        when(bookmarkRepository.findByUser_UserId(1L)).thenReturn(actualBookmarks);

        //Assertion Test
        List<PostResponseDto> expectedBookmarks = actualBookmarks.stream()
                .map(bookmark -> toPostResponseDtoMapper.apply(bookmark.getPost())).toList();
        Assertions.assertThat(bookmarkService.getBookmarkedPosts(1L))
                .isEqualTo(expectedBookmarks);
    }
}
