package com.example.khaier.service;

import com.example.khaier.dto.request.InKindDonationRequestDto;
import com.example.khaier.dto.response.InKindDonationResponseDto;
import com.example.khaier.entity.InKindCase;

import java.util.List;

public interface InKindDonationsService {
    List<InKindCase> getAllInKindDonationCases();

    InKindDonationResponseDto addDonation(InKindDonationRequestDto requestDto);
}
