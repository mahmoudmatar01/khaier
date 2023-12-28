package com.example.khaier.dto.response;

import lombok.Builder;

@Builder
public record BannerResponseDto(
        Long Id,
        String title,
        String description,
        String imageUrl
) {
}
