package com.example.khaier.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record CharityCategoryResponseDto(
        Long categoryId,
        String categoryTitle,
        List<CaseResponseDto> caseList
) {

}
