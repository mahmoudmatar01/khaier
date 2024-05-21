package com.example.khaier.service;

import com.example.khaier.dto.request.GiftRequestDto;
import com.example.khaier.dto.response.GiftResponseDto;
import com.example.khaier.enums.GiftDonationType;

import java.util.List;

public interface GiftDonationService {
    GiftResponseDto save(GiftRequestDto giftRequestDto);
    List<GiftResponseDto> findAllGiftDonationsBySenderId();
    List<GiftDonationType> getAllEnumValues();
    GiftResponseDto findGiftDonation(Long id);
}
