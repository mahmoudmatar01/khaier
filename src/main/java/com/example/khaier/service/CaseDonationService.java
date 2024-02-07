package com.example.khaier.service;

import com.example.khaier.dto.request.CaseDonationRequestDto;
import com.example.khaier.dto.response.CaseDonationResponseDto;

import java.util.List;

public interface CaseDonationService {
    CaseDonationResponseDto createDonation(CaseDonationRequestDto requestDto);
    List<CaseDonationResponseDto> findDonationByCharityId(Long charityId);
    List<CaseDonationResponseDto> findDonationByCaseId(Long caseId);
}
