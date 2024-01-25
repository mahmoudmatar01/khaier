package com.example.khaier.service;

import com.example.khaier.dto.request.DonationCampaignRequestDto;
import com.example.khaier.dto.response.DonationCampaignResponseDto;

import java.util.List;

public interface CampaignService {
    void saveCampaign(DonationCampaignRequestDto requestDto);
    List<DonationCampaignResponseDto> findAllCampaigns();
}
