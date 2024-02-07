package com.example.khaier.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude
public record InKindCaseResponseDto(
        Long inKindCaseId,
        String title,
        String includedItemName,
        List<InKindDonationResponseDto>donation
) {
}
