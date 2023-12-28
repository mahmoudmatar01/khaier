package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.BannerRequestDto;
import com.example.khaier.dto.response.BannerResponseDto;
import com.example.khaier.entity.banner.Banner;
import com.example.khaier.entity.user.User;
import com.example.khaier.enums.Role;
import com.example.khaier.exceptions.BadRequestException;
import com.example.khaier.mapper.BannerRequestDtoToBanner;
import com.example.khaier.mapper.BannerToBannerResponseDto;
import com.example.khaier.repository.banner.BannerRepository;
import com.example.khaier.repository.user.UserRepository;
import com.example.khaier.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final BannerRequestDtoToBanner bannerRequestMapper;
    private final BannerToBannerResponseDto bannerToBannerResponseDto;
    private final UserRepository userRepository;


    @Override
    public List<BannerResponseDto> findAllBanners(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("No user found with this id"));
        if(user.getUserRole() == Role.ROLE_USER)
            throw new BadRequestException("User not authorized to get all banners");
        return bannerRepository.findAll().stream().map(b->
            bannerToBannerResponseDto.apply(b)).toList();
    }

    @Override
    public BannerResponseDto save(BannerRequestDto bannerRequest, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("No user found with this id"));
        if(user.getUserRole() == Role.ROLE_USER)
            throw new BadRequestException("User not authorized to get this service");
        Banner banner = bannerRequestMapper.apply(bannerRequest);
        banner = bannerRepository.save(banner);
        return bannerToBannerResponseDto.apply(banner);
    }
}
