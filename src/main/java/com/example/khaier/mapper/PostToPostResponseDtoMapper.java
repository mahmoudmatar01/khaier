package com.example.khaier.mapper;

import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.entity.Post;
import com.example.khaier.entity.PostImage;
import com.example.khaier.entity.User;
import com.example.khaier.helper.LikeHelper;
import com.example.khaier.helper.TimeSinceFormatterHelper;
import com.example.khaier.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PostToPostResponseDtoMapper implements Function<Post, PostResponseDto> {
    private final LikeHelper likeHelper;
    private final BookmarkRepository bookmarkRepository;

    private final TimeSinceFormatterHelper timeSinceFormatterHelper=TimeSinceFormatterHelper.getInstance();

    @Override
    public PostResponseDto apply(Post post) {
        return apply(post,null);
    }
    public PostResponseDto apply(Post post, Long userId) {
        boolean isLiked = (userId != null) && likeHelper.isUserLikedPost(post.getPostId(), userId);
        boolean isSaved = isPostSavedByUser(post.getPostId(), userId);
        return PostResponseDto
                .builder()
                .id(post.getPostId())
                .content(post.getPostContent())
                .dateTime(post.getDate())
                .createdSince(timeSinceFormatterHelper.formatTimeSince(post.getDate()))
                .imagesUrl(post.getImages()!=null?post.getImages().stream().map(PostImage::getUrl).toList():new ArrayList<>())
                .userId(post.getUser().getUserId())
                .isUserLike(isLiked)
                .userName(post.getUser().getUsername())
                .userImage(post.getUser().getUserImageUrl())
                .numberOfLikes(post.getLikes().size())
                .numberOfComments(post.getComments().size())
                .isSavedOrNot(isSaved)
                .build();
    }
    public boolean isPostSavedByUser(Long postId, Long userId) {
        return bookmarkRepository.existsByUser_UserIdAndPost_PostId(userId, postId);
    }
}
