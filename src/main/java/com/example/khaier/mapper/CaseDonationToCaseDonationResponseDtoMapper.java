package com.example.khaier.mapper;

import com.example.khaier.dto.response.CaseDonationResponseDto;
import com.example.khaier.entity.CaseDonation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CaseDonationToCaseDonationResponseDtoMapper implements Function<CaseDonation, CaseDonationResponseDto> {
    @Override
    public CaseDonationResponseDto apply(CaseDonation caseDonation) {
        return CaseDonationResponseDto.builder()
                .userId(caseDonation.getUser().getUserId())
                .userName(caseDonation.getUser().getUsername())
                .userImageUrl(caseDonation.getUser().getUserImageUrl())
                .caseId(caseDonation.getDonationCase().getCaseId())
                .caseTitle(caseDonation.getDonationCase().getCaseName())
                .charityId(caseDonation.getOrganization().getOrgId())
                .charityName(caseDonation.getOrganization().getOrgName())
                .charityImageUrl(caseDonation.getOrganization().getCharitableOrgImage().getUrl())
                .amount(caseDonation.getAmount())
                .donationWay(caseDonation.getDonationWay())
                .donationTime(caseDonation.getDonationTime())
                .build();
    }
}
