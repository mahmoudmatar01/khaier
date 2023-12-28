package com.example.khaier.service;

import com.example.khaier.dto.request.BannerRequestDto;
import com.example.khaier.dto.response.BannerResponseDto;

import java.util.List;

public interface BannerService {
    List<BannerResponseDto> findAllBanners();
    BannerResponseDto save(BannerRequestDto bannerRequest,Long userId);
}
