package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.BannerRequestDto;
import com.example.khaier.dto.response.BannerResponseDto;
import com.example.khaier.entity.Banner;
import com.example.khaier.entity.User;
import com.example.khaier.enums.Role;
import com.example.khaier.exceptions.BadRequestException;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.BannerRequestDtoToBanner;
import com.example.khaier.mapper.BannerToBannerResponseDto;
import com.example.khaier.repository.BannerRepository;
import com.example.khaier.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final BannerRequestDtoToBanner bannerRequestMapper;
    private final BannerToBannerResponseDto bannerToBannerResponseDto;
    private final UserHelper userHelper;


    @Override
    public List<BannerResponseDto> findAllBanners() {
        List<Banner>banners=bannerRepository.findAll();
        return banners.stream().map(bannerToBannerResponseDto).collect(Collectors.toList());
    }


    @Override
    public BannerResponseDto save(BannerRequestDto bannerRequest,Long userId) {
        User user = userHelper.findUserByIdOrThrowNotFoundException(userId);
        if(user.getUserRole() != Role.ROLE_ADMIN)
            throw new BadRequestException("User not authorized to get this service");
        Banner banner = bannerRequestMapper.apply(bannerRequest);
        return bannerToBannerResponseDto.apply(bannerRepository.save(banner));
    }
}
