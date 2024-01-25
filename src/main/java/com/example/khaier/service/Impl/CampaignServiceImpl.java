package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.DonationCampaignRequestDto;
import com.example.khaier.dto.response.DonationCampaignResponseDto;
import com.example.khaier.entity.DonationCampaign;
import com.example.khaier.mapper.CampaignRequestDtoToCampaignMapper;
import com.example.khaier.mapper.CampaignToCampaignResponseDtoMapper;
import com.example.khaier.repository.CampaignRepository;
import com.example.khaier.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignRequestDtoToCampaignMapper toCampaignMapper;
    private final CampaignToCampaignResponseDtoMapper toCampaignResponseDtoMapper;
    @Override
    public void saveCampaign(DonationCampaignRequestDto requestDto) {
        DonationCampaign campaign=toCampaignMapper.apply(requestDto);
        campaignRepository.save(campaign);
    }

    @Override
    public List<DonationCampaignResponseDto> findAllCampaigns() {
        List<DonationCampaign>campaigns=campaignRepository.findAll();
        return campaigns.stream().map(toCampaignResponseDtoMapper).toList();
    }
}
