package com.example.khaier.dto.response;

import com.example.khaier.enums.GiftDonationType;
import lombok.Builder;

@Builder
public record GiftResponseDto (
        Long id,
        String senderName,
        String senderPhone,
        GiftDonationType giftDonationType,
        float amount,
        String receiverName,
        String receiverPhone,
        String message
) {
}
