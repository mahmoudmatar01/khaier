package com.example.khaier.service.Impl;

import com.example.khaier.dto.response.LikeResponseDto;
import com.example.khaier.entity.Like;
import com.example.khaier.entity.Post;
import com.example.khaier.entity.user.User;
import com.example.khaier.mapper.LikeToLikeResponseDtoMapper;
import com.example.khaier.repository.LikeRepository;
import com.example.khaier.repository.PostRepository;
import com.example.khaier.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeToLikeResponseDtoMapper toLikeResponseDtoMapper;

    public LikeResponseDto addOrRemoveLike(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post with id : "+postId+" not found!"));

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new NotFoundException("User with id : "+userId+" not found!")
        );
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
}
