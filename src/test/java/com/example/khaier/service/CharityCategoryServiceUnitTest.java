package com.example.khaier.service;

import com.example.khaier.dto.request.CharityCategoryRequestDto;
import com.example.khaier.dto.response.CharityCategoryResponseDto;
import com.example.khaier.entity.CharitableOrganization;
import com.example.khaier.entity.DonationCategories;
import com.example.khaier.exceptions.NotFoundCustomException;
import com.example.khaier.helper.CharityOrgHelper;
import com.example.khaier.mapper.CharityCategoryRequestDtoToCharityCategoryMapper;
import com.example.khaier.mapper.CharityCategoryToCharityCategoryResponseDtoMapper;
import com.example.khaier.repository.DonationCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharityCategoryServiceUnitTest {

    @Autowired
    private CharityCategoryService charityCategoryService;

    @MockBean
    private DonationCategoryRepository categoryRepository;

    @MockBean
    private CharityOrgHelper charityOrgHelper;

    @MockBean
    private CharityCategoryRequestDtoToCharityCategoryMapper toCharityCategoryMapper;

    @MockBean
    private CharityCategoryToCharityCategoryResponseDtoMapper toCharityCategoryResponseDtoMapper;

    @Test
    public void testSaveCategory() {
        // Mock data
        CharityCategoryRequestDto requestDto = new CharityCategoryRequestDto("Test Category", 1L);
        DonationCategories mockCategory = new DonationCategories(null, "Test Category", null, null);
        CharityCategoryResponseDto expectedResponse = new CharityCategoryResponseDto(1L, "Test Category", Collections.emptyList());

        CharitableOrganization mockCharityOrg = CharitableOrganization.builder()
                .donationCategories(new ArrayList<>())
                .build();

        // Mock behavior
        Mockito.when(toCharityCategoryMapper.apply(requestDto)).thenReturn(mockCategory);
        Mockito.when(categoryRepository.save(mockCategory)).thenReturn(mockCategory);

        // Mock the charitableOrganization
        mockCategory.setCharitableOrganization(mockCharityOrg);
        Mockito.when(toCharityCategoryResponseDtoMapper.apply(mockCategory)).thenReturn(expectedResponse);

        // Call the service method
        CharityCategoryResponseDto actualResponse = charityCategoryService.saveCategory(requestDto);

        // Assertions
        assertEquals(expectedResponse.categoryId(), actualResponse.categoryId());
        assertEquals(expectedResponse.categoryTitle(), actualResponse.categoryTitle());
        assertEquals(expectedResponse.caseList(), actualResponse.caseList());
    }

    @Test(expected = NotFoundCustomException.class)
    public void testFindCharityCategories_charityNotFound() {
        Long charityId = 1L;

        // Mock charityOrgHelper.findCharityByIdOrThrowNotFound
        Mockito.doThrow(new NotFoundCustomException("Charity not found with id: " + charityId)).when(charityOrgHelper).findCharityByIdOrThrowNotFound(charityId);

        // Call the service method (should throw exception)
        charityCategoryService.findCharityCategories(charityId);
    }

    @Test
    public void testFindCharityCategoryById_existingCategory() {
        Long categoryId = 10L;

        // Mock categoryRepository.findById
        DonationCategories mockCategory = new DonationCategories();
        mockCategory.setCategoryId(categoryId);
        mockCategory.setCategoryTitle("Test Category");
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(mockCategory));

        // Mock toCharityCategoryResponseDtoMapper
        Mockito.when(toCharityCategoryResponseDtoMapper.apply(Mockito.any(DonationCategories.class))).thenReturn(new CharityCategoryResponseDto(categoryId, "Test Category", null));

        // Call the service method
        CharityCategoryResponseDto responseDto = charityCategoryService.findCharityCategoryById(categoryId);

        // Assertions
        assertNotNull(responseDto);
        assertEquals(categoryId, responseDto.categoryId());
        assertEquals("Test Category", responseDto.categoryTitle());
    }

    @Test(expected = NotFoundCustomException.class)
    public void testFindCharityCategoryById_notFound() {
        Long categoryId = 10L;

        // Mock categoryRepository.findById to return empty optional
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Call the service method (should throw exception)
        charityCategoryService.findCharityCategoryById(categoryId);
    }

}
