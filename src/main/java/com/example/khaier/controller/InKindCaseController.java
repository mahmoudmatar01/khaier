package com.example.khaier.controller;

import com.example.khaier.dto.request.InKindCaseRequestDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.InKindCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.version}/in-kind-cases")
@RequiredArgsConstructor
public class InKindCaseController {
    private final SuccessResponseFactory200 responseFactory;
    private final InKindCaseService inKindCaseService;

    @GetMapping
    public ResponseEntity<?> getAllInKindCases() {
        var result = inKindCaseService.findAllInKindCase();
        var responseBody = responseFactory.createResponse(result,"InKind cases returned successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<?> addInKindCase(@RequestBody InKindCaseRequestDto requestDto){
        var result = inKindCaseService.saveInKindCase(requestDto);
        var responseBody = responseFactory.createResponse(result,"InKind case save successfully!");
        return ResponseEntity.ok(responseBody);
    }
}
