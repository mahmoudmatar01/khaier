package com.example.khaier.service;

import com.example.khaier.dto.request.CampaignRequestDto;
import com.example.khaier.dto.response.CampaignResponseDto;

import java.util.List;

public interface CampaignService {
    void saveCampaign(CampaignRequestDto requestDto);
    List<CampaignResponseDto> findAllCampaigns();
}
