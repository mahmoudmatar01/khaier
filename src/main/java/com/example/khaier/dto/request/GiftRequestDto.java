package com.example.khaier.dto.request;

import com.example.khaier.enums.GiftDonationType;
import lombok.Builder;

@Builder
public record GiftRequestDto (
        String senderName,
        String senderPhone,
        GiftDonationType giftDonationType,
        float amount,
        String receiverName,
        String receiverPhone,
        String message
){
}
