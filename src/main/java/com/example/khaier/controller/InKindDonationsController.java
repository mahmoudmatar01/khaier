package com.example.khaier.controller;


import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.InKindDonationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/in-kind-donations")
@RequiredArgsConstructor
public class InKindDonationsController {

    private final SuccessResponseFactory200 responseFactory;
    private final InKindDonationsService inKindDonationsService;

    @GetMapping("/cases")
    public ResponseEntity<?> getAllInKindDonationCases() {
        var result = inKindDonationsService.getAllInKindDonationCases();
        var responseBody = responseFactory.createResponse(result,null);
        return ResponseEntity.ok(responseBody);
    }
}
