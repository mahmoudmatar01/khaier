package com.example.khaier.service;

import com.example.khaier.dto.request.GiftRequestDto;
import com.example.khaier.dto.response.GiftResponseDto;

import java.util.List;

public interface GiftDonationService {
    GiftResponseDto save(GiftRequestDto giftRequestDto, Long userId);
    List<GiftResponseDto> findAllGiftDonationsBySenderId(Long id);
    GiftResponseDto findGiftDonation(Long id);
}
