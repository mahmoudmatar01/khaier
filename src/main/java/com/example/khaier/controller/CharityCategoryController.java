package com.example.khaier.controller;

import com.example.khaier.dto.request.CaseDonationRequestDto;
import com.example.khaier.dto.request.CaseRequestDto;
import com.example.khaier.dto.request.CharityCategoryRequestDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.CaseDonationService;
import com.example.khaier.service.CharityCategoryService;
import com.example.khaier.service.DonationCategoryCasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/charity-category")
@RequiredArgsConstructor
public class CharityCategoryController {
    private final SuccessResponseFactory200 responseFactory;
    private final CharityCategoryService charityCategoryService;
    private final DonationCategoryCasesService casesService;
    private final CaseDonationService donationService;

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
    @GetMapping("/case/{categoryId}")
    public ResponseEntity<?> getAllCase(@PathVariable Long categoryId) {
        var result = casesService.findCaseByCategoryId(categoryId);
        var responseBody = responseFactory.createResponse(result,"Cases returned successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/case")
    public ResponseEntity<?> addCase(@RequestBody CaseRequestDto requestDto){
        var result = casesService.saveCase(requestDto);
        var responseBody = responseFactory.createResponse(result,"Case save successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/case/donate")
    public ResponseEntity<?> createDonation(@RequestBody CaseDonationRequestDto requestDto){
        var result = donationService.createDonation(requestDto);
        var responseBody = responseFactory.createResponse(result,"Donation done successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/case/donate/{caseId}")
    public ResponseEntity<?> geTDonationInCase(@PathVariable Long caseId){
        var result = donationService.findDonationByCaseId(caseId);
        var responseBody = responseFactory.createResponse(result,"Donation returned successfully!");
        return ResponseEntity.ok(responseBody);
    }
}
