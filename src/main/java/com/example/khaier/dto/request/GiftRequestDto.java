package com.example.khaier.dto.request;

import com.example.khaier.enums.GiftDonationType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GiftRequestDto (
        @NotBlank String senderPhone,
        @NotBlank GiftDonationType giftDonationType,
        @NotBlank BigDecimal amount,
        @NotBlank String receiverName,
        @NotBlank String receiverPhone,
        @NotBlank String message
){
}
