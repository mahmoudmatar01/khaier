package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.InKindDonationRequestDto;
import com.example.khaier.dto.response.InKindDonationResponseDto;
import com.example.khaier.entity.InKindDonation;
import com.example.khaier.helper.CharityOrgHelper;
import com.example.khaier.mapper.InKindDonationRequestDtoToInKindDonationMapper;
import com.example.khaier.mapper.InKindDonationToInKindDonationResponseDtoMapper;
import com.example.khaier.repository.InKindDonationRepository;
import com.example.khaier.service.InKindDonationsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class InKindDonationsServiceImpl implements InKindDonationsService {

    private final InKindDonationRequestDtoToInKindDonationMapper toInKindDonationMapper;
    private final InKindDonationToInKindDonationResponseDtoMapper toInKindDonationResponseDtoMapper;
    private final InKindDonationRepository inKindDonationRepository;
    private final CharityOrgHelper charityOrgHelper;


    @Override
    public List<InKindDonationResponseDto> getAllInKindDonation() {
        return inKindDonationRepository.findAll().stream().map(
                toInKindDonationResponseDtoMapper
        ).toList();
    }

    @Override
    public List<InKindDonationResponseDto> getAllInKindDonationByCharityId(Long charityId) {
        charityOrgHelper.findCharityByIdOrThrowNotFound(charityId);
        List<InKindDonation>inKindDonations=inKindDonationRepository.findByOrganization_OrgId(charityId);
        return inKindDonations.stream().map(toInKindDonationResponseDtoMapper).toList();
    }

    @Override
    public InKindDonationResponseDto addDonation(InKindDonationRequestDto requestDto) {
        InKindDonation donation = toInKindDonationMapper.apply(requestDto);
        donation = inKindDonationRepository.save(donation);
        donation.getInKindCase().getDonations().add(donation);
        return toInKindDonationResponseDtoMapper.apply(donation);
    }

}
