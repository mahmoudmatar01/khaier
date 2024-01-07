package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.PostRequestDto;
import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.entity.post.Post;
import com.example.khaier.entity.user.User;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.PostRequestDtoToPostMapper;
import com.example.khaier.mapper.PostToPostResponseDtoMapper;
import com.example.khaier.repository.post.PostRepository;
import com.example.khaier.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.io.IOException;
import java.util.ArrayList;
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
    public List<PostResponseDto> getAllPosts(Long userId) {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post->postToPostResponseDtoMapper.apply(post,userId)).collect(Collectors.toList());
    }

    @Override
    public PostResponseDto addNewPost(PostRequestDto postDto,Long userId) {
        User user=userHelper.checkUserIsExistOrThrowException(userId);
        Post post = requestDtoToPostMapper.apply(postDto);
        post.setUser(user);
        post = postRepository.save(post);
        return postToPostResponseDtoMapper.apply(post,userId);
    }

    @Override
    public PostResponseDto getPostById(Long postId,Long userId) {
        Post post =postRepository.findById(postId).orElseThrow(
                ()->new NotFoundException("Post with id : "+postId+" not found!")
        );
        return postToPostResponseDtoMapper.apply(post,userId);
    }
}
