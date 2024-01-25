package com.example.khaier.helper;

import com.example.khaier.entity.Post;
import com.example.khaier.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
@RequiredArgsConstructor
public class PostHelper {

    private final PostRepository postRepository;
    public Post findPostByIdOrThrowNotFound(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post with id : "+postId+" not found!"));
    }

    public Boolean checkExistenceOfPostById(Long postId){
        return postRepository.existsByPostId(postId);
    }
}
