package com.example.khaier.service;

import com.example.khaier.dto.request.PostRequestDto;
import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.entity.Post;
import com.example.khaier.entity.User;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.PostRequestDtoToPostMapper;
import com.example.khaier.mapper.PostToPostResponseDtoMapper;
import com.example.khaier.repository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class PostServiceTest {
    @MockBean
    private PostRepository postRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRequestDtoToPostMapper toPostMapper;
    @Autowired
    private PostToPostResponseDtoMapper toPostResponseDtoMapper;
    private final User user = Mockito.mock(User.class);
    private final UserHelper userHelperMock = Mockito.mock(UserHelper.class);

    @BeforeEach
    void init(){
        Mockito.when(userHelperMock.findUserByIdOrThrowNotFoundException(1L)).thenReturn(user);
    }

    @Test
    public void whenCreatePost_thenReturnPostResponseDto(){
        // Mock MultipartFile For Image
        byte[] content = "Mock image content".getBytes();
        MultipartFile imageFile = new MockMultipartFile("image", "image.jpg", "image/jpeg", content);
        MultipartFile[] images = new MultipartFile[]{imageFile};
        PostRequestDto postRequestDto = PostRequestDto.builder()
                .postContent("post content 1")
                .image(images)
                .build();
        Post post = toPostMapper.apply(postRequestDto);
        post.setUser(user);
        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);
        Assertions.assertThat(postService.addNewPost(postRequestDto, 1L).content())
                .isEqualTo(toPostResponseDtoMapper.apply(post).content());
    }

    @Test
    void whenGetAllPostsByUserIdPageable_thenReturnPostResponseDtoList(){
        Post post1 = Post.builder().postId(1L).postContent("post 1")
                .date(LocalDateTime.now()).user(user)
                .comments(new ArrayList<>())
                .likes(new ArrayList<>()).build();
        Post post2 = Post.builder().postId(2L).postContent("post 2")
                .date(LocalDateTime.now()).user(user)
                .comments(new ArrayList<>())
                .likes(new ArrayList<>()).build();
        Pageable firstPage = PageRequest.of(0, 10);
        Page<Post> posts = new PageImpl<>(List.of(post1, post2), firstPage, 2);
        List<PostResponseDto> postResponseDtos = posts.getContent()
                .stream()
                .map(post -> toPostResponseDtoMapper.apply(post, 1L))
                .toList();
        Mockito.when(postRepository.findAll(firstPage)).thenReturn(posts);
        Assertions.assertThat(postService.getAllPosts(1L, firstPage).size()).isEqualTo(postResponseDtos.size());
    }

    @Test
    void whenFindPostById_thenReturnPost(){
        // Mock MultipartFile For Post Image
        byte[] content = "Mock image content".getBytes();
        MultipartFile[] imageFile = new MockMultipartFile[]{
                new MockMultipartFile("image1", "image.jpg", "image/jpeg", content),
                new MockMultipartFile("image2", "image.jpg", "image/jpeg", content)};

        PostRequestDto postRequestDto = PostRequestDto.builder()
                .image(imageFile).postContent("post content mock").build();
        Post actualPost = toPostMapper.apply(postRequestDto);
        actualPost.setUser(user);
        PostResponseDto expectedPost = toPostResponseDtoMapper.apply(actualPost);
        Mockito.when(postRepository.findById(1L)).thenReturn(Optional.of(actualPost));
        Assertions.assertThat(postService.getPostById(1L, 1L).content()).isEqualTo(expectedPost.content());
    }
}
