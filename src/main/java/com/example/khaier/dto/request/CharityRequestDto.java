package com.example.khaier.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record CharityRequestDto(
        @NotBlank MultipartFile image,
        @NotBlank String charityName,
        @NotBlank String charityDescription,
        @NotBlank String charityLocation,
        @NotBlank String facebookPageUrl,
        @NotBlank String instagramPageUrl,
        @NotBlank String whatsappNumber,
        @NotBlank String phoneNumber

) {
}
