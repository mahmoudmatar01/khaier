package com.example.khaier.controller;

import com.example.khaier.dto.request.GiftRequestDto;
import com.example.khaier.dto.response.GiftResponseDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.GiftDonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gift-donation")
@RequiredArgsConstructor
public class GiftDonationController {
    private final SuccessResponseFactory200 responseFactory;
    private final GiftDonationService giftDonationService;

    @GetMapping
    public ResponseEntity<?> getAllGiftDonationsByUserId(@RequestParam Long userId){
        List<GiftResponseDto> response = giftDonationService.findAllGiftDonationsBySenderId(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Gift Donations returned successfully "));
    }

    @GetMapping("/category")
    public ResponseEntity<?> getAllEnumValues(){
        var response = giftDonationService.getAllEnumValues();
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Gift Donations categories returned successfully "));
    }

    @PostMapping
    public ResponseEntity<?> saveGiftDonation(@RequestParam Long userId, @RequestBody GiftRequestDto giftRequestDto){
        giftDonationService.save(giftRequestDto, userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(null,"The Gift Donation has been saved"));
    }

    @GetMapping("/{donationId}")
    public ResponseEntity<?> findGiftDonation(@PathVariable Long donationId){
        GiftResponseDto giftResponseDto = giftDonationService.findGiftDonation(donationId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(giftResponseDto, "Gift Donation returned successfully"));
    }
}
