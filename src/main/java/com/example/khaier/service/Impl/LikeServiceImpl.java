package com.example.khaier.service.Impl;

import com.example.khaier.dto.response.LikeResponseDto;
import com.example.khaier.entity.Like;
import com.example.khaier.entity.post.Post;
import com.example.khaier.entity.user.User;
import com.example.khaier.helper.PostHelper;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.LikeToLikeResponseDtoMapper;
import com.example.khaier.repository.LikeRepository;
import com.example.khaier.service.LikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostHelper postHelper;
    private final UserHelper userHelper;
    private final LikeToLikeResponseDtoMapper toLikeResponseDtoMapper;
    @Override
    public LikeResponseDto addOrRemoveLike(Long postId, Long userId) {
        Post post = postHelper.checkPostExistOrThrowException(postId);
        User user=userHelper.checkUserIsExistOrThrowException(userId);
        Like existingLike = likeRepository.findByPostAndUser(post, user);
        if (existingLike != null) {
            existingLike.setLiked(false);
            likeRepository.delete(existingLike);
            return toLikeResponseDtoMapper.apply(existingLike);
        } else {
            Like newLike = Like
                    .builder()
                    .isLiked(true)
                    .date(LocalDateTime.now())
                    .post(post)
                    .user(user)
                    .build();
            newLike=likeRepository.save(newLike);
            post.getLikes().add(newLike);
            return toLikeResponseDtoMapper.apply(newLike);
        }
    }

    @Override
    public List<LikeResponseDto> findLikesByPostId(Long postId) {
        Post post = postHelper.checkPostExistOrThrowException(postId);
        List<Like>likes=likeRepository.findByPost(post);
        return likes.stream().map(toLikeResponseDtoMapper).toList();
    }


}
