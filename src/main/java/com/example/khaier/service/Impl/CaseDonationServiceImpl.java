package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.CaseDonationRequestDto;
import com.example.khaier.dto.response.CaseDonationResponseDto;
import com.example.khaier.entity.CaseDonation;
import com.example.khaier.entity.DonationCategoryCase;
import com.example.khaier.exceptions.NotFoundCustomException;
import com.example.khaier.helper.CharityOrgHelper;
import com.example.khaier.mapper.CaseDonationRequestDtoToCaseDonationMapper;
import com.example.khaier.mapper.CaseDonationToCaseDonationResponseDtoMapper;
import com.example.khaier.repository.CaseDonationRepository;
import com.example.khaier.repository.DonationCategoryCaseRepository;
import com.example.khaier.service.CaseDonationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CaseDonationServiceImpl implements CaseDonationService {
    private final CaseDonationRepository donationRepository;
    private final CaseDonationRequestDtoToCaseDonationMapper toCaseDonationMapper;
    private final CharityOrgHelper charityOrgHelper;
    private final CaseDonationToCaseDonationResponseDtoMapper toCaseDonationResponseDtoMapper;
    private final DonationCategoryCaseRepository caseRepository;
    @Override
    public CaseDonationResponseDto createDonation(CaseDonationRequestDto requestDto) {
        CaseDonation donation=toCaseDonationMapper.apply(requestDto);
        DonationCategoryCase categoryCase=findCaseOrThrowException(requestDto.caseId());
        updateCaseAttributes(categoryCase, requestDto.amount());
        donation=donationRepository.save(donation);
        donation.getDonationCase().getDonations().add(donation);
        return toCaseDonationResponseDtoMapper.apply(donation);
    }

    @Override
    public List<CaseDonationResponseDto> findDonationByCharityId(Long charityId) {
        charityOrgHelper.findCharityByIdOrThrowNotFound(charityId);
        return donationRepository.findByOrganization_OrgId(charityId).stream().map(toCaseDonationResponseDtoMapper).toList();
    }

    @Override
    public List<CaseDonationResponseDto> findDonationByCaseId(Long caseId) {
        findCaseOrThrowException(caseId);
        return donationRepository.findByDonationCase_CaseId(caseId).stream().map(toCaseDonationResponseDtoMapper).toList();
    }

    private DonationCategoryCase findCaseOrThrowException(Long caseId){
        return caseRepository.findById(caseId).orElseThrow(
                ()-> new NotFoundCustomException("Case with id : "+caseId+" not found!")
        );
    }
    private void updateCaseAttributes(DonationCategoryCase donationCase, BigDecimal donationAmount) {
        donationCase.setPaidAmount(donationCase.getPaidAmount().add(donationAmount));
        donationCase.setRemainingAmount(donationCase.getRequiredAmount().subtract(donationCase.getPaidAmount()));
        caseRepository.save(donationCase);
    }
}
