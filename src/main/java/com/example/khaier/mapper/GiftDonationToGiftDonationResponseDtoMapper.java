package com.example.khaier.mapper;

import com.example.khaier.dto.response.GiftResponseDto;
import com.example.khaier.entity.GiftDonation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GiftDonationToGiftDonationResponseDtoMapper implements Function<GiftDonation, GiftResponseDto> {

    @Override
    public GiftResponseDto apply(GiftDonation giftDonation) {
        return GiftResponseDto.builder()
                .id(giftDonation.getId())
                .giftDonationType(giftDonation.getGiftDonationType())
                .amount(giftDonation.getAmount())
                .senderName(giftDonation.getSender().getUsername())
                .senderPhone(giftDonation.getSender().getPhone())
                .receiverName(giftDonation.getReceiverName())
                .receiverPhone(giftDonation.getReceiverPhone())
                .message(giftDonation.getMessage())
                .build();
    }
}
