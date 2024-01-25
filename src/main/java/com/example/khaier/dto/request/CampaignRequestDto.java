package com.example.khaier.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
public record CampaignRequestDto(
       @NotBlank Long charityId,
       @NotBlank String campaignName,
       @NotBlank String campaignAdditionalName,
       @NotBlank String campaignDescription,
       @NotBlank MultipartFile image,
       @NotBlank
       LocalDateTime campaignEndDay,
       @NotBlank Long numberOfBeneficiaries,
       @NotBlank double amountRequired
) {
}
