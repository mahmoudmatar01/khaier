package com.example.khaier.service;

import com.example.khaier.dto.request.CaseRequestDto;
import com.example.khaier.dto.response.CaseResponseDto;

import java.util.List;

public interface DonationCategoryCasesService {
    List<CaseResponseDto>findCaseByCategoryId(Long categoryId);
    CaseResponseDto saveCase(CaseRequestDto caseRequestDto);
}
