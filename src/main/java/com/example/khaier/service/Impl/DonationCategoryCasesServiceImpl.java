package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.CaseRequestDto;
import com.example.khaier.dto.response.CaseResponseDto;
import com.example.khaier.entity.DonationCategoryCase;
import com.example.khaier.exceptions.NotFoundCustomException;
import com.example.khaier.mapper.CaseRequestDtoToDonationCaseMapper;
import com.example.khaier.mapper.DonationCaseToCaseResponseMapper;
import com.example.khaier.repository.DonationCategoryCaseRepository;
import com.example.khaier.repository.DonationCategoryRepository;
import com.example.khaier.service.DonationCategoryCasesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationCategoryCasesServiceImpl implements DonationCategoryCasesService {
    private final DonationCategoryRepository categoryRepository;
    private final DonationCategoryCaseRepository caseRepository;
    private final DonationCaseToCaseResponseMapper toCaseResponseMapper;
    private final CaseRequestDtoToDonationCaseMapper toDonationCaseMapper;
    @Override
    public List<CaseResponseDto> findCaseByCategoryId(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(
                ()-> new NotFoundCustomException("Category with id : "+categoryId+" not found!")
        );
        return caseRepository.findByDonationCategory_CategoryId(categoryId).stream().map(toCaseResponseMapper).toList();
    }

    @Override
    public CaseResponseDto saveCase(CaseRequestDto caseRequestDto) {
        DonationCategoryCase saveCase=toDonationCaseMapper.apply(caseRequestDto);
        caseRepository.save(saveCase);
        saveCase.getDonationCategory().getDonationCases().add(saveCase);
        return toCaseResponseMapper.apply(saveCase);
    }

    @Override
    public CaseResponseDto findCaseById(Long caseId) {
        DonationCategoryCase donationCategoryCase=caseRepository.findById(caseId).orElseThrow(
                ()-> new NotFoundCustomException("Case with id : "+caseId+" not found!")
        );
        return toCaseResponseMapper.apply(donationCategoryCase);
    }
}
