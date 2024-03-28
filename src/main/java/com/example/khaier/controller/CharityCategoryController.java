package com.example.khaier.controller;

import com.example.khaier.dto.request.CharityCategoryRequestDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.CharityCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.version}/charity-category")
@RequiredArgsConstructor
public class CharityCategoryController {
    private final SuccessResponseFactory200 responseFactory;
    private final CharityCategoryService charityCategoryService;


    @GetMapping
    public ResponseEntity<?> getCharityCategories(@RequestParam Long charityId) {
        var result = charityCategoryService.findCharityCategories(charityId);
        var responseBody = responseFactory.createResponse(result,"Categories returned successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCharityCategory(@PathVariable Long categoryId) {
        var result = charityCategoryService.findCharityCategoryById(categoryId);
        var responseBody = responseFactory.createResponse(result,"Category returned successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<?> addCharityCategory(@RequestBody CharityCategoryRequestDto requestDto){
        var result = charityCategoryService.saveCategory(requestDto);
        var responseBody = responseFactory.createResponse(result,"Category save successfully!");
        return ResponseEntity.ok(responseBody);
    }
}
