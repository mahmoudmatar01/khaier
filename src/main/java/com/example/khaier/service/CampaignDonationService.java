package com.example.khaier.service;
import com.example.khaier.dto.response.CampaignDonationResponseDto;

import java.math.BigDecimal;
import java.util.List;


public interface CampaignDonationService {
    void donateToCampaign(Long userId, Long campaignId, BigDecimal amount);
    List<CampaignDonationResponseDto>getByUserId(Long userId);
}
