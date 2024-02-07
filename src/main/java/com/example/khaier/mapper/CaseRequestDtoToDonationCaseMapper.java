package com.example.khaier.mapper;

import com.example.khaier.dto.request.CaseRequestDto;
import com.example.khaier.entity.DonationCategoryCase;
import com.example.khaier.entity.DonationCategories;
import com.example.khaier.exceptions.NotFoundCustomException;
import com.example.khaier.repository.DonationCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CaseRequestDtoToDonationCaseMapper implements Function<CaseRequestDto, DonationCategoryCase> {
    private final DonationCategoryRepository categoryRepository;
    @Override
    public DonationCategoryCase apply(CaseRequestDto caseRequestDto) {
        return DonationCategoryCase.builder()
                .caseName(caseRequestDto.caseName())
                .maxim(caseRequestDto.hekma())
                .description(caseRequestDto.description())
                .requiredAmount(caseRequestDto.requiredAmount())
                .remainingAmount(caseRequestDto.requiredAmount())
                .paidAmount(BigDecimal.valueOf(0))
                .donationCategory(findCategoryByIdOrThrowException(caseRequestDto.categoryId()))
                .donations(new ArrayList<>())
                .build();
    }
    private DonationCategories findCategoryByIdOrThrowException(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow(
                ()->new NotFoundCustomException("category with id : "+categoryId+" not found!")
        );
    }
}
