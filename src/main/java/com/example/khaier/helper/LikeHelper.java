package com.example.khaier.helper;

import com.example.khaier.entity.post.Post;
import com.example.khaier.entity.user.User;
import com.example.khaier.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeHelper {

    private final UserHelper userHelper;
    private final LikeRepository likeRepository;
    public boolean isUserLikedPost(Post post, Long userId) {
        User user =userHelper.checkUserIsExistOrThrowException(userId);
        return likeRepository.existsByPostAndUser(post, user);
    }
}
