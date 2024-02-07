package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.CharityCategoryRequestDto;
import com.example.khaier.dto.response.CharityCategoryResponseDto;
import com.example.khaier.entity.DonationCategories;
import com.example.khaier.exceptions.NotFoundCustomException;
import com.example.khaier.helper.CharityOrgHelper;
import com.example.khaier.mapper.CharityCategoryRequestDtoToCharityCategoryMapper;
import com.example.khaier.mapper.CharityCategoryToCharityCategoryResponseDtoMapper;
import com.example.khaier.repository.DonationCategoryRepository;
import com.example.khaier.service.CharityCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CharityCategoryServiceImpl implements CharityCategoryService {

    private final DonationCategoryRepository categoryRepository;
    private final CharityOrgHelper charityOrgHelper;
    private final CharityCategoryRequestDtoToCharityCategoryMapper toCharityCategoryMapper;
    private final CharityCategoryToCharityCategoryResponseDtoMapper toCharityCategoryResponseDtoMapper;
    @Override
    public CharityCategoryResponseDto saveCategory(CharityCategoryRequestDto requestDto) {
        DonationCategories category=toCharityCategoryMapper.apply(requestDto);
        category=categoryRepository.save(category);
        category.getCharitableOrganization().getDonationCategories().add(category);
        return toCharityCategoryResponseDtoMapper.apply(category);
    }

    @Override
    public List<CharityCategoryResponseDto> findCharityCategories(Long charityId) {
        charityOrgHelper.findCharityByIdOrThrowNotFound(charityId);
        return categoryRepository.findByCharitableOrganization_OrgId(charityId).stream()
                .map(toCharityCategoryResponseDtoMapper).toList();
    }

    @Override
    public CharityCategoryResponseDto findCharityCategoryById(Long categoryId) {
        var category=categoryRepository.findById(categoryId).orElseThrow(
                ()->new NotFoundCustomException("Category with id : "+categoryId+" not found!")
        );
        return toCharityCategoryResponseDtoMapper.apply(category);
    }
}
