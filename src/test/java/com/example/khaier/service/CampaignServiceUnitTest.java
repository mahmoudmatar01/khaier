package com.example.khaier.service;

import com.example.khaier.dto.request.CampaignRequestDto;
import com.example.khaier.dto.response.CampaignResponseDto;
import com.example.khaier.entity.Campaign;
import com.example.khaier.exceptions.NotFoundCustomException;
import com.example.khaier.helper.CharityOrgHelper;
import com.example.khaier.mapper.CampaignRequestDtoToCampaignMapper;
import com.example.khaier.mapper.CampaignToCampaignResponseDtoMapper;
import com.example.khaier.repository.CampaignRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CampaignServiceUnitTest {
    @Autowired
    private CampaignService campaignService;

    @MockBean
    private CampaignRepository campaignRepository;

    @MockBean
    private CampaignRequestDtoToCampaignMapper toCampaignMapper;

    @MockBean
    private CampaignToCampaignResponseDtoMapper toCampaignResponseDtoMapper;

    @MockBean
    private CharityOrgHelper charityOrgHelper;

    @Test
    public void testSaveCampaign_validInput() {
        // Create mock CampaignRequestDto
        CampaignRequestDto requestDto = CampaignRequestDto.builder()
                .charityId(1L)
                .campaignName("Test Campaign")
                .campaignAdditionalName("Additional Info")
                .campaignDescription("This is a test campaign description")
                .image(null)
                .campaignEndDay(LocalDateTime.now())
                .numberOfBeneficiaries(100L)
                .amountRequired(1000.00)
                .build();

        // Mock campaignRepository.save
        Campaign mockCampaign = new Campaign();
        Mockito.when(campaignRepository.save(Mockito.any(Campaign.class))).thenReturn(mockCampaign);

        // Mock toCampaignMapper.apply
        Mockito.when(toCampaignMapper.apply(requestDto)).thenReturn(mockCampaign);

        // Call the service method
        campaignService.saveCampaign(requestDto);

        // Verify interactions
        Mockito.verify(campaignRepository, Mockito.times(1)).save(Mockito.any(Campaign.class));
    }

    @Test(expected = NotFoundCustomException.class)
    public void testFindAllCampaigns_charityNotFound() {
        Long charityId = 1L;

        // Mock charityOrgHelper.findCharityByIdOrThrowNotFound
        Mockito.doThrow(new NotFoundCustomException("Charity not found with id: " + charityId)).when(charityOrgHelper).findCharityByIdOrThrowNotFound(charityId);

        // Call the service method (should throw exception)
        campaignService.findAllCampaigns(charityId);
    }

    @Test
    public void testFindAllCampaigns_validCharity() {
        Long charityId = 1L;
        List<Campaign> mockCampaigns = new ArrayList<>();

        Campaign mockCampaign = new Campaign();
        mockCampaign.setCampaignId(10L);
        mockCampaign.setCampaignName("Test Campaign Name");

        mockCampaigns.add(mockCampaign);

        // Mock campaignRepository.findByCharitableOrganization_OrgId
        Mockito.when(campaignRepository.findByCharitableOrganization_OrgId(charityId)).thenReturn(mockCampaigns);

        // Mock toCampaignResponseDtoMapper to return a specific CampaignResponseDto
        CampaignResponseDto expectedResponseDto = new CampaignResponseDto(
                mockCampaign.getCampaignId(),
                mockCampaign.getCampaignName(),
                "Test Campaign Additional Name",
                "Test Campaign Description",
                "www.campaign-image.com",
                null,
                435L,
                123.34,
                1L,
                "Charity Name",
                "www.charity-image.com"
        );
        Mockito.when(toCampaignResponseDtoMapper.apply(Mockito.any(Campaign.class))).thenReturn(expectedResponseDto);

        // Call the service method
        List<CampaignResponseDto> responseDtos = campaignService.findAllCampaigns(charityId);

        // Assertions
        assertNotNull(responseDtos);
        assertEquals(1, responseDtos.size()); // Assert there's one element in the list

        // Assert values of the first element
        CampaignResponseDto actualResponseDto = responseDtos.get(0);
        assertEquals(expectedResponseDto.campaignId(), actualResponseDto.campaignId());
        assertEquals(expectedResponseDto.campaignName(), actualResponseDto.campaignName());
    }

}
