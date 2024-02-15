package com.example.khaier.helper;
import com.example.khaier.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeHelper {

    private final UserHelper userHelper;
    private final LikeRepository likeRepository;
    public boolean isUserLikedPost(Long postId, Long userId) {
        userHelper.findUserByIdOrThrowNotFoundException(userId);
        return likeRepository.existsByPost_PostIdAndUser_UserId(postId, userId);
    }
}
