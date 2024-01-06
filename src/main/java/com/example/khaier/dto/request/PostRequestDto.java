package com.example.khaier.dto.request;
import lombok.Builder;

@Builder
public record PostRequestDto(
        String postContent
) {
}
