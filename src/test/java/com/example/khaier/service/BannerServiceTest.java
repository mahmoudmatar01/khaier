package com.example.khaier.service;

import com.example.khaier.dto.request.BannerRequestDto;
import com.example.khaier.dto.response.BannerResponseDto;
import com.example.khaier.entity.Banner;
import com.example.khaier.entity.User;
import com.example.khaier.enums.Role;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.BannerToBannerResponseDto;
import com.example.khaier.repository.BannerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@SpringBootTest
public class BannerServiceTest {
    @MockBean
    private BannerRepository bannerRepository;
    private final BannerService bannerService;
    private final BannerToBannerResponseDto toBannerResponseDto;

    @Autowired
    public BannerServiceTest(BannerService bannerService,BannerToBannerResponseDto toBannerResponseDto) {
        this.bannerService = bannerService;
        this.toBannerResponseDto=toBannerResponseDto;
    }
    @Test
    public void whenFindAll_ReturnBannerList(){
        // Repository Mockup
        Banner banner=Banner.builder()
                .bannerId(1L).title("First Banner Title!").description("First Banner Description").imageUrl("www.first-banner-image.com")
                .build();
        Banner banner2=Banner.builder()
                .bannerId(2L).title("Second Banner Title!").description("Second Banner Description").imageUrl("www.second-banner-image.com")
                .build();
        List<Banner> bannerList= Arrays.asList(banner,banner2);
        given(bannerRepository.findAll()).willReturn(bannerList);

        // Assertion Test
        assertThat(bannerService.findAllBanners())
                .contains(toBannerResponseDto.apply(banner),toBannerResponseDto.apply(banner2))
                .hasSize(2);
    }
    @Test
    public void whenPostBanner_thenCreateBanner() {
        // Mock Admin User
        User userMock = Mockito.mock(User.class);
        Mockito.when(userMock.getUserRole()).thenReturn(Role.ROLE_ADMIN);
        UserHelper userHelperMock = Mockito.mock(UserHelper.class);
        Mockito.when(userHelperMock.findUserByIdOrThrowNotFoundException(1L)).thenReturn(userMock);

        // Mock MultipartFile For Image
        byte[] content = "Mock image content".getBytes();
        MultipartFile imageFile = new MockMultipartFile("image", "image.jpg", "image/jpeg", content);

        // Create a banner request dto
        BannerRequestDto requestDto = BannerRequestDto.builder()
                .title("Banner Title!")
                .description("Banner Description")
                .image(imageFile)
                .build();

        // Create a banner entity from banner request dto
        Banner savedBanner = Banner.builder()
                .bannerId(1L)
                .title(requestDto.title())
                .description(requestDto.description())
                .imageUrl("www.banner-image.com")
                .build();

        // Repository Mockup
        given(bannerRepository.save(any(Banner.class))).willReturn(savedBanner);

        // Call the Service Method
        BannerResponseDto responseDto = bannerService.save(requestDto, 1L);

        // Assertion Test
        assertEquals(savedBanner.getDescription(), responseDto.description());
    }
}
