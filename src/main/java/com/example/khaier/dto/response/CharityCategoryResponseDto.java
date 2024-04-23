package com.example.khaier.dto.response;

import lombok.Builder;

@Builder
public record CharityCategoryResponseDto(
        Long categoryId,
        String categoryTitle
) {

}
