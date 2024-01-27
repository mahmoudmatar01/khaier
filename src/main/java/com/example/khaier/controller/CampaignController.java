package com.example.khaier.controller;

import com.example.khaier.dto.request.CampaignRequestDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.CampaignService;
import com.example.khaier.service.Impl.CampaignImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/campaign")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignImageService campaignImageService;
    private final SuccessResponseFactory200 responseFactory;
    private final CampaignService campaignService;

    @GetMapping("/{charityId}")
    public ResponseEntity<?> getAllCampaigns(@PathVariable Long charityId){
        var response = campaignService.findAllCampaigns(charityId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Campaigns returned successfully "));
    }
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> saveCampaign(@ModelAttribute CampaignRequestDto campaignRequestDto){
        campaignService.saveCampaign(campaignRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(null,"Campaign data saved successfully"));
    }

    @GetMapping(value = "/image/{title}", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getImage(@PathVariable String title){
        byte[] imageData = campaignImageService.downloadImage(title);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
