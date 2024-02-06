package com.example.khaier.service;

import com.example.khaier.dto.request.InKindCaseRequestDto;
import com.example.khaier.dto.response.InKindCaseResponseDto;

import java.util.List;

public interface InKindCaseService {
    List<InKindCaseResponseDto>findAllInKindCase();
    InKindCaseResponseDto saveInKindCase(InKindCaseRequestDto requestDto);
}
