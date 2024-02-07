package com.example.khaier.mapper;

import com.example.khaier.dto.response.InKindDonationResponseDto;
import com.example.khaier.entity.InKindDonation;
import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
public class InKindDonationToInKindDonationResponseDtoMapper implements Function<InKindDonation, InKindDonationResponseDto> {
    @Override
    public InKindDonationResponseDto apply(InKindDonation inKindDonation) {
        return InKindDonationResponseDto
                .builder()
                .donationId(inKindDonation.getDonationId())
                .userId(inKindDonation.getUser().getUserId())
                .userName(inKindDonation.getUser().getUsername())
                .phone(inKindDonation.getPhone())
                .organizationId(inKindDonation.getOrganization().getOrgId())
                .organizationName(inKindDonation.getOrganization().getOrgName())
                .caseId(inKindDonation.getInKindCase().getId())
                .caseTitle(inKindDonation.getInKindCase().getTitle())
                .itemAmount(inKindDonation.getItemAmount())
                .itemName(inKindDonation.getItemName())
                .lat(inKindDonation.getLat())
                .lang(inKindDonation.getLang())
                .addressDescription(inKindDonation.getAddressDescription())
                .donationTime(inKindDonation.getDonationTime())
                .build();
    }
}
