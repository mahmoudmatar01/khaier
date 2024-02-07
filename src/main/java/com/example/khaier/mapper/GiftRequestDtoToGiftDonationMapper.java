package com.example.khaier.mapper;

import com.example.khaier.dto.request.GiftRequestDto;
import com.example.khaier.entity.GiftDonation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GiftRequestDtoToGiftDonationMapper implements Function<GiftRequestDto, GiftDonation> {

    @Override
    public GiftDonation apply(GiftRequestDto giftRequestDto) {
        return GiftDonation.builder()
                .giftDonationType(giftRequestDto.giftDonationType())
                .amount(giftRequestDto.amount())
                .message(giftRequestDto.message())
                .receiverName(giftRequestDto.receiverName())
                .receiverPhone(giftRequestDto.receiverPhone()).build();
    }
}
