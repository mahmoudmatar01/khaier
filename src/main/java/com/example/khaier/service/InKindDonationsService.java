package com.example.khaier.service;
import com.example.khaier.dto.request.InKindDonationRequestDto;
import com.example.khaier.dto.response.InKindDonationResponseDto;
import java.util.List;

public interface InKindDonationsService {
    List<InKindDonationResponseDto> getAllInKindDonation();
    List<InKindDonationResponseDto> getAllInKindDonationByCharityId(Long charityId);
    InKindDonationResponseDto addDonation(InKindDonationRequestDto requestDto);
}
