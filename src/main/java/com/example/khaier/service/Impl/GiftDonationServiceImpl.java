package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.GiftRequestDto;
import com.example.khaier.dto.response.GiftResponseDto;
import com.example.khaier.entity.GiftDonation;
import com.example.khaier.entity.User;
import com.example.khaier.enums.GiftDonationType;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.GiftDonationToGiftDonationResponseDtoMapper;
import com.example.khaier.mapper.GiftDonationRequestDtoToGiftDonationMapper;
import com.example.khaier.repository.GiftDonationRepository;
import com.example.khaier.service.GiftDonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftDonationServiceImpl implements GiftDonationService {
    private final GiftDonationRepository giftDonationRepository;
    private final UserHelper userHelper;
    private final GiftDonationRequestDtoToGiftDonationMapper toGiftDonationMapper;
    private final GiftDonationToGiftDonationResponseDtoMapper toGiftResponseDtoMapper;

    @Override
    public GiftResponseDto save(GiftRequestDto giftRequestDto) {
        User user = (User)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("88888888888888888888888888888888888888888888");
        System.out.println(user.getUserId());
        System.out.println("88888888888888888888888888888888888888888888");
        GiftDonation giftDonation = toGiftDonationMapper.apply(giftRequestDto,user.getUserId());
        System.out.println("88888888888888888888888888888888888888888888");
        System.out.println(giftDonation.getSender());
        System.out.println(giftDonation.getAmount());
        System.out.println("88888888888888888888888888888888888888888888");
        giftDonation=giftDonationRepository.save(giftDonation);
        System.out.println("88888888888888888888888888888888888888888888");
        System.out.println(giftDonation.getId());
        System.out.println("88888888888888888888888888888888888888888888");
        return toGiftResponseDtoMapper.apply(giftDonation);
    }

    @Override
    public List<GiftResponseDto> findAllGiftDonationsBySenderId() {
        User user = (User)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userHelper.findUserByIdOrThrowNotFoundException(user.getUserId());
        List<GiftDonation> giftDonations=giftDonationRepository.findAllBySender_UserId(user.getUserId());
        return giftDonations.stream().map(toGiftResponseDtoMapper).toList();
    }

    @Override
    public List<GiftDonationType> getAllEnumValues() {
        return Arrays.asList(GiftDonationType.values());
    }

    @Override
    public GiftResponseDto findGiftDonation(Long id) {
        GiftDonation giftDonation= giftDonationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Gift Donation with id : "+id+" is not found!"));
        return toGiftResponseDtoMapper.apply(giftDonation);
    }
}
