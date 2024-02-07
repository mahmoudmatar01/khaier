package com.example.khaier.controller;


import com.example.khaier.dto.request.InKindDonationRequestDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.InKindDonationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/in-kind-donations")
@RequiredArgsConstructor
public class InKindDonationsController {

    private final SuccessResponseFactory200 responseFactory;
    private final InKindDonationsService inKindDonationsService;

    @GetMapping
    public ResponseEntity<?> getAllInKindDonation() {
        var result = inKindDonationsService.getAllInKindDonation();
        var responseBody = responseFactory.createResponse(result,"InKind donations returned successfully!");
        return ResponseEntity.ok(responseBody);
    }
    @GetMapping("/{charityId}")
    public ResponseEntity<?> getAllInKindDonationByCharityId(@PathVariable Long charityId) {
        var result = inKindDonationsService.getAllInKindDonationByCharityId(charityId);
        var responseBody = responseFactory.createResponse(result,"InKind donations returned successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<?> addInKindDonation(@RequestBody InKindDonationRequestDto requestDto){
        var result = inKindDonationsService.addDonation(requestDto);
        var responseBody = responseFactory.createResponse(result,"InKind donation save successfully!");
        return ResponseEntity.ok(responseBody);
    }
}
