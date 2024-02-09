package com.example.khaier.service.Impl;

import com.example.khaier.dto.response.CampaignDonationResponseDto;
import com.example.khaier.entity.Campaign;
import com.example.khaier.entity.CampaignDonation;
import com.example.khaier.entity.User;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.CampaignDonationToCampaignDonationResponseDtoMapper;
import com.example.khaier.repository.CampaignDonationRepository;
import com.example.khaier.repository.CampaignRepository;
import com.example.khaier.repository.UserRepository;
import com.example.khaier.service.CampaignDonationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CampaignDonationServiceImpl implements CampaignDonationService {
    private final UserHelper userHelper;
    private final CampaignRepository campaignRepository;
    private final UserRepository userRepository;
    private final CampaignDonationRepository campaignDonationRepository;
    private final CampaignDonationToCampaignDonationResponseDtoMapper toCampaignDonationResponseDtoMapper;
    @Override
    public void donateToCampaign(Long userId, Long campaignId, BigDecimal amount) {
        User user=userHelper.findUserByIdOrThrowNotFoundException(userId);
        Campaign campaign = findCampaign(campaignId);
        CampaignDonation donation = createCampaignDonation(user,campaign,amount);
        userRepository.save(user);
        campaignRepository.save(campaign);
        campaignDonationRepository.save(donation);
        user.getDonations().add(donation);
        campaign.getDonations().add(donation);
    }

    @Override
    public List<CampaignDonationResponseDto> getByUserId(Long userId) {
        userHelper.findUserByIdOrThrowNotFoundException(userId);
        List<CampaignDonation>campaignDonations=campaignDonationRepository.findByUser_UserId(userId);
        return campaignDonations.stream().map(toCampaignDonationResponseDtoMapper).toList();
    }

    private Campaign findCampaign(Long id){
        return campaignRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Campaign not found with id: " + id));
    }

    private CampaignDonation createCampaignDonation(User user,Campaign campaign,BigDecimal amount){

        return CampaignDonation.builder()
                .user(user)
                .campaign(campaign)
                .amount(amount)
                .donationTime(LocalDateTime.now())
                .build();
    }
}
