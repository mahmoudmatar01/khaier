package com.example.khaier.controller;

import com.example.khaier.dto.request.CharityRequestDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.CaseDonationService;
import com.example.khaier.service.CharityService;
import com.example.khaier.service.Impl.CharityImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${api.version}/charity")
@RequiredArgsConstructor
public class CharityController {
    private final SuccessResponseFactory200 responseFactory;
    private final CharityImageService charityImageService;
    private final CharityService charityService;
    private final CaseDonationService donationService;


    @GetMapping
    public ResponseEntity<?> getAllCharities(){
        var response = charityService.getAllCharity();
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Charities returned successfully "));
    }
    @PostMapping(value = "/{adminId}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> saveCharity(@ModelAttribute CharityRequestDto charityRequestDto, @PathVariable Long adminId){
        var response = charityService.saveCharity(charityRequestDto,adminId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(response,"Charity data saved successfully"));
    }
    @GetMapping("/{charityId}")
    public ResponseEntity<?> getCharityByID(@PathVariable Long charityId){
        var result = charityService.getCharityById(charityId);
        var responseBody = responseFactory.createResponse(result,"Charity returned successfully!");
        return ResponseEntity.ok(responseBody);
    }
    @GetMapping("/donation/{charityId}")
    public ResponseEntity<?> geTDonationInCharity(@PathVariable Long charityId){
        var result = donationService.findDonationByCharityId(charityId);
        var responseBody = responseFactory.createResponse(result,"Donations returned successfully!");
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping(value = "/image/{title}", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> getImage(@PathVariable String title){
        byte[] imageData = charityImageService.downloadImage(title);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
