package com.example.khaier.dto.response;

import com.example.khaier.enums.GiftDonationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@JsonInclude
public record GiftResponseDto (
        Long id,
        String senderName,
        String senderPhone,
        GiftDonationType giftDonationType,
        BigDecimal amount,
        String receiverName,
        String receiverPhone,
        String message
) {
}
