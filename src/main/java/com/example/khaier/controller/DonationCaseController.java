package com.example.khaier.controller;

import com.example.khaier.dto.request.CaseDonationRequestDto;
import com.example.khaier.dto.request.CaseRequestDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.CaseDonationService;
import com.example.khaier.service.DonationCategoryCasesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.version}/donation-case")
@RequiredArgsConstructor
public class DonationCaseController {
    private final SuccessResponseFactory200 responseFactory;
    private final DonationCategoryCasesService casesService;
    private final CaseDonationService donationService;

    @GetMapping
    public ResponseEntity<?> getAllCase(@RequestParam Long categoryId) {
        var result = casesService.findCaseByCategoryId(categoryId);
        var responseBody = responseFactory.createResponse(result,"Cases returned successfully!");
        return ResponseEntity.ok(responseBody);
    }
    @GetMapping("/{caseId}")
    public ResponseEntity<?> getCaseById(@PathVariable Long caseId) {
        var result = casesService.findCaseById(caseId);
        var responseBody = responseFactory.createResponse(result,"Case returned successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<?> addCase(@RequestBody CaseRequestDto requestDto){
        var result = casesService.saveCase(requestDto);
        var responseBody = responseFactory.createResponse(result,"Case saved successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/donate")
    public ResponseEntity<?> createDonation(@RequestBody CaseDonationRequestDto requestDto){
        var result = donationService.createDonation(requestDto);
        var responseBody = responseFactory.createResponse(result,"Donation done successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/donations/{caseId}")
    public ResponseEntity<?> geTDonationInCase(@PathVariable Long caseId){
        var result = donationService.findDonationByCaseId(caseId);
        var responseBody = responseFactory.createResponse(result,"Donations returned successfully!");
        return ResponseEntity.ok(responseBody);
    }
}
