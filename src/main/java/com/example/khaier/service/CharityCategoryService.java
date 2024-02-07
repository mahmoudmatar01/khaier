package com.example.khaier.service;

import com.example.khaier.dto.request.CharityCategoryRequestDto;
import com.example.khaier.dto.response.CharityCategoryResponseDto;

import java.util.List;

public interface CharityCategoryService {
    CharityCategoryResponseDto saveCategory(CharityCategoryRequestDto requestDto);
    List<CharityCategoryResponseDto> findCharityCategories(Long charityId);
    CharityCategoryResponseDto findCharityCategoryById(Long categoryId);
}
