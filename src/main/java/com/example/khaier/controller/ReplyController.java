package com.example.khaier.controller;
import com.example.khaier.dto.request.ReplyRequestDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.Impl.ReplyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.version}/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final SuccessResponseFactory200 responseFactory;
    private final ReplyServiceImpl replyService;

    @PostMapping
    public ResponseEntity<?> saveReply(@RequestBody ReplyRequestDto requestDto){
        var response = replyService.addReply(requestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Reply saved successfully "));
    }
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<?> findByCommentId(@PathVariable Long commentId){
        var response = replyService.getByCommentId(commentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Replies returned successfully "));
    }
}
