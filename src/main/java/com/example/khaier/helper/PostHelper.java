package com.example.khaier.helper;

import com.example.khaier.entity.post.Post;
import com.example.khaier.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
@RequiredArgsConstructor
public class PostHelper {

    private final PostRepository postRepository;
    public Post checkPostExistOrThrowException(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post with id : "+postId+" not found!"));
    }
}
