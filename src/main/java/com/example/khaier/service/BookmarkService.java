package com.example.khaier.service;

import com.example.khaier.dto.response.PostResponseDto;

import java.util.List;

public interface BookmarkService {
    void savePostToBookmark(Long userId,Long postId);
    List<PostResponseDto> getBookmarkedPosts(Long userId);
}
