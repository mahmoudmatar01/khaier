package com.example.khaier.mapper;

import com.example.khaier.dto.response.CaseResponseDto;
import com.example.khaier.entity.DonationCategoryCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class DonationCaseToCaseResponseMapper implements Function<DonationCategoryCase, CaseResponseDto> {
    private final CaseDonationToCaseDonationResponseDtoMapper toCaseDonationResponseDtoMapper;
    @Override
    public CaseResponseDto apply(DonationCategoryCase donationCase) {
        return CaseResponseDto.builder()
                .caseId(donationCase.getCaseId())
                .caseName(donationCase.getCaseName())
                .hekma(donationCase.getMaxim())
                .description(donationCase.getDescription())
                .requiredAmount(donationCase.getRequiredAmount())
                .paidAmount(donationCase.getPaidAmount())
                .remainingAmount(donationCase.getRemainingAmount())
                .categoryId(donationCase.getDonationCategory().getCategoryId())
                .donations(donationCase.getDonations().stream().map(toCaseDonationResponseDtoMapper).toList())
                .build();
    }
}
