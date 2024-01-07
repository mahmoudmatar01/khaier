package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.PostRequestDto;
import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.entity.Post;
import com.example.khaier.entity.user.User;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.PostRequestDtoToPostMapper;
import com.example.khaier.mapper.PostToPostResponseDtoMapper;
import com.example.khaier.repository.PostRepository;
import com.example.khaier.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostRequestDtoToPostMapper requestDtoToPostMapper;
    private final PostToPostResponseDtoMapper postToPostResponseDtoMapper;
    private final UserHelper userHelper;
    @Override
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(postToPostResponseDtoMapper).collect(Collectors.toList());
    }

    @Override
    public PostResponseDto addNewPost(PostRequestDto postDto,Long userId) {
        User user=userHelper.checkUserIsExistOrThrowException(userId);
        Post post = requestDtoToPostMapper.apply(postDto);
        post.setUser(user);
        post = postRepository.save(post);
        return postToPostResponseDtoMapper.apply(post);
    }

    @Override
    public PostResponseDto getPostById(Long postId) {
        Post post =postRepository.findById(postId).orElseThrow(
                ()->new NotFoundException("Post with id : "+postId+" not found!")
        );
        return postToPostResponseDtoMapper.apply(post);
    }
}
