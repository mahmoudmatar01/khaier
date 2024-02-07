package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.GiftRequestDto;
import com.example.khaier.dto.response.GiftResponseDto;
import com.example.khaier.entity.GiftDonation;
import com.example.khaier.entity.User;
import com.example.khaier.mapper.GiftDonationToGiftResponseDtoMapper;
import com.example.khaier.mapper.GiftRequestDtoToGiftDonationMapper;
import com.example.khaier.repository.GiftDonationRepository;
import com.example.khaier.repository.UserRepository;
import com.example.khaier.service.GiftDonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class GiftDonationServiceImpl implements GiftDonationService {
    private final GiftDonationRepository giftDonationRepository;
    private final UserRepository userRepository;
    private final GiftRequestDtoToGiftDonationMapper toGiftDonationMapper;
    private final GiftDonationToGiftResponseDtoMapper toGiftResponseDtoMapper;

    public GiftDonationServiceImpl(GiftDonationRepository giftDonationRepository, UserRepository userRepository,
                                   GiftRequestDtoToGiftDonationMapper toGiftDonationMapper,
                                   GiftDonationToGiftResponseDtoMapper toGiftResponseDtoMapper) {
        this.giftDonationRepository = giftDonationRepository;
        this.userRepository = userRepository;
        this.toGiftDonationMapper = toGiftDonationMapper;
        this.toGiftResponseDtoMapper = toGiftResponseDtoMapper;
    }

    @Override
    public GiftResponseDto save(GiftRequestDto giftRequestDto, Long userId) {
        GiftDonation giftDonation = toGiftDonationMapper.apply(giftRequestDto);
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User with id : "+userId+" is not found!"));
        giftDonation.setSender(user);
        return toGiftResponseDtoMapper.apply(giftDonationRepository.save(giftDonation));
    }

    @Override
    public List<GiftResponseDto> findAllGiftDonationsBySenderId(Long id) {
        List<GiftDonation> giftDonations=giftDonationRepository.findAllBySender_UserId(id);
        return giftDonations.stream().map(toGiftResponseDtoMapper).toList();
    }

    @Override
    public GiftResponseDto findGiftDonation(Long id) {
        GiftDonation giftDonation= giftDonationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Gift Donation with id : "+id+" is not found!"));
        return toGiftResponseDtoMapper.apply(giftDonation);
    }
}
