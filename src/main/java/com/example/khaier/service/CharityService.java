package com.example.khaier.service;

import com.example.khaier.dto.request.CharityRequestDto;
import com.example.khaier.dto.response.CharityResponseDto;

import java.util.List;

public interface CharityService {
    CharityResponseDto saveCharity(CharityRequestDto requestDto,Long adminId);
    List<CharityResponseDto> getAllCharity();
}
