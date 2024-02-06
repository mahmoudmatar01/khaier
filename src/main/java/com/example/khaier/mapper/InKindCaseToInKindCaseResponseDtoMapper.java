package com.example.khaier.mapper;

import com.example.khaier.dto.response.InKindCaseResponseDto;
import com.example.khaier.entity.InKindCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InKindCaseToInKindCaseResponseDtoMapper implements Function<InKindCase, InKindCaseResponseDto> {

    private final InKindDonationToInKindDonationResponseDtoMapper toInKindDonationResponseDtoMapper;
    @Override
    public InKindCaseResponseDto apply(InKindCase inKindCase) {
        return InKindCaseResponseDto
                .builder()
                .inKindCaseId(inKindCase.getId())
                .title(inKindCase.getTitle())
                .includedItemName(inKindCase.getIncludedItemName())
                .donation(inKindCase.getDonations().stream().map(toInKindDonationResponseDtoMapper).toList())
                .build();
    }
}
