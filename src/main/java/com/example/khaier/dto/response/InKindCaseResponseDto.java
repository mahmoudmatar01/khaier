package com.example.khaier.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;


@Builder
@JsonInclude
public record InKindCaseResponseDto(
        Long inKindCaseId,
        String title,
        String includedItemName
) {
}
