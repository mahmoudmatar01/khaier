package com.example.khaier.service.Impl;

import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.entity.Bookmark;
import com.example.khaier.entity.Post;
import com.example.khaier.entity.User;
import com.example.khaier.helper.PostHelper;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.PostToPostResponseDtoMapper;
import com.example.khaier.repository.BookmarkRepository;
import com.example.khaier.service.BookmarkService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkServiceImpl implements BookmarkService {

    private final UserHelper userHelper;
    private final PostHelper postHelper;
    private final BookmarkRepository bookmarkRepository;
    private final PostToPostResponseDtoMapper toPostResponseDtoMapper;

    @Override
    public void savePostToBookmark(Long userId, Long postId) {
        User user=userHelper.findUserByIdOrThrowNotFoundException(userId);
        Post post=postHelper.findPostByIdOrThrowNotFound(postId);
        Bookmark bookmark=Bookmark.
                builder()
                .date(LocalDateTime.now())
                .user(user)
                .post(post)
                .build();
        bookmarkRepository.save(bookmark);
    }

    @Override
    public List<PostResponseDto> getBookmarkedPosts(Long userId) {
        User user=userHelper.findUserByIdOrThrowNotFoundException(userId);
        List<Bookmark>bookmarks=bookmarkRepository.findByUser(user);
        List<Post>posts= bookmarks.stream()
                .map(Bookmark::getPost)
                .toList();
        return posts.stream().map(toPostResponseDtoMapper).toList();
    }
}
