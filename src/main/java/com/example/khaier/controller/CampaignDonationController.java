package com.example.khaier.controller;

import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.CampaignDonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/${api.version}/campaign-donation")
@RequiredArgsConstructor
public class CampaignDonationController {

    private final SuccessResponseFactory200 responseFactory;
    private final CampaignDonationService campaignDonationService;

    @PostMapping
    public ResponseEntity<?> saveCampaignDonation(@RequestParam Long userId, @RequestParam Long campaignId, @RequestParam BigDecimal amount){
        campaignDonationService.donateToCampaign(userId,campaignId,amount);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(null,"Campaign Donation Done"));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> saveCampaignDonation(@PathVariable Long userId){
        var response=campaignDonationService.getByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Donations returned successfully"));
    }
}
