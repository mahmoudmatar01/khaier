package com.example.khaier.controller;

import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.Impl.LikeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.version}/like")
@RequiredArgsConstructor
public class LikeController {
    private final SuccessResponseFactory200 responseFactory;
    private final LikeServiceImpl likeService;

    @PostMapping
    public ResponseEntity<?> addOrRemoveLike(@RequestParam Long postId) {
        var response = likeService.addOrRemoveLike(postId);
        if(response.isLiked()){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseFactory.createResponse(response, "liked returned successfully "));
        }else{
            return ResponseEntity.status(HttpStatus.OK)
                    .body(responseFactory.createResponse(response, "liked removed successfully "));
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> findByPostId(@PathVariable Long postId){
        var response = likeService.findLikesByPostId(postId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Likes returned successfully "));
    }
}