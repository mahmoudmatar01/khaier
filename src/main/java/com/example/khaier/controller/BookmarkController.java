package com.example.khaier.controller;

import com.example.khaier.dto.response.PostResponseDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

    private final SuccessResponseFactory200 responseFactory;
    private final BookmarkService bookmarkService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getSavedPosts(@PathVariable Long userId){
        List<PostResponseDto> response = bookmarkService.getBookmarkedPosts(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Posts returned successfully "));
    }

    @PostMapping
    public ResponseEntity<?> savePostToBookmark(@RequestParam Long userId,@RequestParam Long postId){
        bookmarkService.savePostToBookmark(userId,postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(null,"The post has been saved"));
    }
}
