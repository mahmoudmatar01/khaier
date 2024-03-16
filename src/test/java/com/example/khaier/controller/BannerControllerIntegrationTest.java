package com.example.khaier.controller;

import com.example.khaier.dto.request.BannerRequestDto;
import com.example.khaier.dto.response.BannerResponseDto;
import com.example.khaier.entity.User;
import com.example.khaier.enums.Role;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.models.ApiCustomResponse;
import com.example.khaier.service.BannerService;
import com.example.khaier.service.Impl.BannerImageServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Banner Controller Integration Test")
@Tag("Integration")
public class BannerControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    String baseUrl = "/api/v1/banner";
    @MockBean
    private BannerService bannerService;
    @MockBean
    private BannerImageServiceImpl bannerImageService;

    @Test
    void testGetAllBanners() throws Exception {
        List<BannerResponseDto> bannerResponseDtos = new ArrayList<>();
        bannerResponseDtos.add(new BannerResponseDto(1L, "banner1", "This is banner1", "blahblahblah"));
        bannerResponseDtos.add(new BannerResponseDto(2L, "banner2", "This is banner2", "lalalalalalalala"));
        when(bannerService.findAllBanners()).thenReturn(bannerResponseDtos);
        ApiCustomResponse apiCustomResponse = new ApiCustomResponse<>("Banners returned successfully", bannerResponseDtos,
                200, true);
        ObjectMapper mapper = new ObjectMapper();
        String expectedData = mapper.writeValueAsString(apiCustomResponse);
        this.mockMvc.perform(MockMvcRequestBuilders.get(baseUrl).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().json(expectedData));
    }

    @Test
    void testGetBannerImage() throws Exception {
        byte[] content = "Mock image content".getBytes();
        MultipartFile imageFile = new MockMultipartFile("image", "image.jpg", "image/jpeg", content);
        when(bannerImageService.downloadImage(imageFile.getName()+ "-" + Mockito.anyString())).thenReturn(content);
        this.mockMvc.perform(MockMvcRequestBuilders.get(baseUrl +
                        "/image/image.jpg-5g2f3v4848gd7410").accept(MediaType.valueOf("image/png")))
                .andExpect(status().is(200))
                .andExpect(content().contentType(MediaType.valueOf("image/png;charset=UTF-8")));
    }

    @Test
    void testSaveBanner() throws Exception {
        User userMock = Mockito.mock(User.class);
        Mockito.when(userMock.getUserRole()).thenReturn(Role.ROLE_ADMIN);
        UserHelper userHelperMock = Mockito.mock(UserHelper.class);
        Mockito.when(userHelperMock.findUserByIdOrThrowNotFoundException(1L)).thenReturn(userMock);
        byte[] content = "Mock image content".getBytes();
        MockMultipartFile imageFile = new MockMultipartFile("image", "image.jpg", "image/jpeg", content);
        BannerRequestDto bannerRequestDto = BannerRequestDto.builder()
                .title("banner3")
                .description("This is banner3")
                .image(imageFile)
                .build();
        BannerResponseDto bannerResponseDto = BannerResponseDto.builder()
                .title(bannerRequestDto.title())
                .description(bannerRequestDto.description())
                .imageUrl("www.img.png")
                .build();
        given(bannerService.save(Mockito.any(BannerRequestDto.class), Mockito.eq(1L))).willReturn(bannerResponseDto);
        this.mockMvc.perform(multipart(baseUrl + "/{userId}", 1L)
                        .file(imageFile)
                        .param("title", "Banner title")
                        .param("description", "Banner description")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isSuccessful").value(true))
                .andExpect(jsonPath("$.message").value("Banner saved successfully"));
    }
}
