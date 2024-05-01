package com.example.khaier.service;

import com.example.khaier.dto.request.GiftRequestDto;
import com.example.khaier.dto.response.GiftResponseDto;
import com.example.khaier.entity.GiftDonation;
import com.example.khaier.entity.User;
import com.example.khaier.enums.GiftDonationType;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.GiftDonationRequestDtoToGiftDonationMapper;
import com.example.khaier.mapper.GiftDonationToGiftDonationResponseDtoMapper;
import com.example.khaier.repository.GiftDonationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Gift Donation Service Unit Test")
public class GiftDonationServiceTest {
    @MockBean
    private GiftDonationRepository giftDonationRepository;
    @Autowired
    private GiftDonationService giftDonationService;
    @Autowired
    private GiftDonationRequestDtoToGiftDonationMapper toGiftDonationMapper;
    @Autowired
    private GiftDonationToGiftDonationResponseDtoMapper toGiftDonationResponseDtoMapper;
    private final User userMock = Mockito.mock(User.class);
    private User userMock2;
    private UserHelper userHelperMock = Mockito.mock(UserHelper.class);

    @BeforeEach
    void init(){
        //Mock 2 users before each test
        Mockito.when(userHelperMock.findUserByIdOrThrowNotFoundException(1L)).thenReturn(userMock);
        userMock2 = Mockito.mock(User.class);
        Mockito.when(userHelperMock.findUserByIdOrThrowNotFoundException(2L)).thenReturn(userMock2);
    }

    @Test
    void testSaveGiftDonation(){
        //Arrange
        GiftRequestDto giftRequestDto = GiftRequestDto.builder()
                .senderName("Ola").senderPhone("025485400")
                .receiverName("Kris").receiverPhone("01610214184")
                .giftDonationType(GiftDonationType.صدقة).amount(BigDecimal.valueOf(410)).message("").build();
        GiftDonation savedGiftDonation = toGiftDonationMapper.apply(giftRequestDto, 1L);
        //Act
        when(giftDonationRepository.save(Mockito.any(GiftDonation.class))).thenReturn(savedGiftDonation);
        //Test the service method
        assertThat(giftDonationService.save(giftRequestDto, 1L)).isEqualTo(
                toGiftDonationResponseDtoMapper.apply(savedGiftDonation));
    }

    @Test
    void testFindGiftDonation(){
        GiftDonation giftDonation = GiftDonation.builder()
                .giftDonationType(GiftDonationType.اطعام_مسكين).amount(BigDecimal.valueOf(8740))
                .message("hope this helps").sender(userMock)
                .receiverName("Ashely").receiverPhone("01485410").build();
        GiftResponseDto giftResponseDto = toGiftDonationResponseDtoMapper.apply(giftDonation);
        when(giftDonationRepository.findById(1L)).thenReturn(Optional.of(giftDonation));
        assertThat(giftDonationService.findGiftDonation(1L)).isEqualTo(giftResponseDto);
    }

    @Test
    void testFindAllGiftDonationsBySenderId(){
        //Create 2 giftDonations for userMock and 1 giftDonation for userMock2
        GiftDonation giftDonation= GiftDonation.builder()
                .id(1L).sender(userMock).giftDonationType(GiftDonationType.سكن)
                .receiverPhone("010954510").receiverName("Grace")
                .message("First Gift Donation by User1").amount(BigDecimal.valueOf(1000))
                .build();
        GiftDonation giftDonation2 = GiftDonation.builder()
                .id(2L).sender(userMock).giftDonationType(GiftDonationType.كساء)
                .receiverPhone("010454510").receiverName("Salem")
                .message("Second Gift Donation by User1").amount(BigDecimal.valueOf(500))
                .build();
        GiftDonation giftDonation3 = GiftDonation.builder()
                .id(3L).sender(userMock2).giftDonationType(GiftDonationType.اطعام_مسكين)
                .receiverPhone("012141320").receiverName("Maggie")
                .message("First Gift Donation by User2").amount(BigDecimal.valueOf(300))
                .build();
        List<GiftDonation> giftDonationsByUser1 = Arrays.asList(giftDonation, giftDonation2,giftDonation3);
        //Convert list of GiftDonations to list of giftResponseDtoList
        List<GiftResponseDto> giftResponseDtosByUser1 = giftDonationsByUser1.stream()
                .map(gift -> toGiftDonationResponseDtoMapper.apply(gift)).toList();
        //Mock the repository
        when(giftDonationRepository.findAllBySender_UserId(1L)).thenReturn(giftDonationsByUser1);
        //Assert Check
        assertThat(giftDonationService.findAllGiftDonationsBySenderId(1L))
                .isEqualTo(giftResponseDtosByUser1);

    }
}
