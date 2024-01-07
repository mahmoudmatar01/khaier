package com.example.khaier.controller;
import com.example.khaier.dto.request.ReplyRequestDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.Impl.ReplyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final SuccessResponseFactory200 responseFactory;
    private final ReplyServiceImpl replyService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> savePost(@RequestBody ReplyRequestDto requestDto, @PathVariable Long userId){
        var response = replyService.addReply(requestDto,userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Reply saved successfully "));
    }
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<?> findByComment(@PathVariable Long commentId){
        var response = replyService.getByCommentId(commentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Replies returned successfully "));
    }
}
