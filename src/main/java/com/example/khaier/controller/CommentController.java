package com.example.khaier.controller;

import com.example.khaier.dto.request.CommentRequestDto;
import com.example.khaier.dto.response.CommentResponseDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.Impl.CommentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final SuccessResponseFactory200 responseFactory;
    private final CommentServiceImpl commentService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> savePost(@RequestBody CommentRequestDto commentRequestDto, @PathVariable Long userId){
        CommentResponseDto response = commentService.addComment(commentRequestDto,userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Comment saved successfully "));
    }
    @GetMapping("/{commentId}")
    public ResponseEntity<?> findById(@PathVariable Long commentId){
        CommentResponseDto response = commentService.getById(commentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Comment returned successfully "));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> findByPost(@PathVariable Long postId){
        List<CommentResponseDto> response = commentService.getCommentsByPostId(postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Comments returned successfully "));
    }
}
