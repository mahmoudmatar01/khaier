package com.example.khaier.mapper;

import com.example.khaier.dto.request.GiftRequestDto;
import com.example.khaier.entity.GiftDonation;
import com.example.khaier.entity.User;
import com.example.khaier.helper.UserHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class GiftDonationRequestDtoToGiftDonationMapper implements Function<GiftRequestDto, GiftDonation> {

    private final UserHelper userHelper;
    @Override
    public GiftDonation apply(GiftRequestDto giftRequestDto) {
        return apply(giftRequestDto,null);
    }
    public GiftDonation apply(GiftRequestDto giftRequestDto,Long userId) {
        User sender=userHelper.findUserByIdOrThrowNotFoundException(userId);
        return GiftDonation.builder()
                .sender(sender)
                .giftDonationType(giftRequestDto.giftDonationType())
                .amount(giftRequestDto.amount())
                .message(giftRequestDto.message())
                .receiverName(giftRequestDto.receiverName())
                .receiverPhone(giftRequestDto.receiverPhone()).build();
    }
}
