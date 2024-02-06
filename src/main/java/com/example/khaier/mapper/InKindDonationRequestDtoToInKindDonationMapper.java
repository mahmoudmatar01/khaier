package com.example.khaier.mapper;

import com.example.khaier.dto.request.InKindDonationRequestDto;
import com.example.khaier.entity.InKindCase;
import com.example.khaier.entity.InKindDonation;
import com.example.khaier.exceptions.NotFoundCustomException;
import com.example.khaier.helper.CharityOrgHelper;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.repository.InKindCasesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InKindDonationRequestDtoToInKindDonationMapper implements Function<InKindDonationRequestDto, InKindDonation> {
    private final UserHelper userHelper;
    private final CharityOrgHelper charityOrgHelper;
    private final InKindCasesRepository inKindCasesRepository;

    @Override
    public InKindDonation apply(InKindDonationRequestDto inKindDonationRequestDto) {
        InKindCase inKindCase=getInKindCaseWithId(inKindDonationRequestDto.inKindCaseId());
        String itemName = inKindCase.getIncludedItemName() == null
                ? inKindDonationRequestDto.itemName()
                : inKindCase.getIncludedItemName();

        return InKindDonation
                .builder()
                .user(userHelper.findUserByIdOrThrowNotFoundException(inKindDonationRequestDto.userId()))
                .phone(inKindDonationRequestDto.phone())
                .organization(charityOrgHelper.findCharityByIdOrThrowNotFound(inKindDonationRequestDto.organizationId()))
                .inKindCase(inKindCase)
                .itemAmount(inKindDonationRequestDto.itemAmount())
                .itemName(itemName)
                .lang(inKindDonationRequestDto.lang())
                .lat(inKindDonationRequestDto.lat())
                .donationTime(LocalDateTime.now())
                .addressDescription(inKindDonationRequestDto.addressDescription())
                .build();
    }

    private InKindCase getInKindCaseWithId(Long id){
        return inKindCasesRepository.findById(id).orElseThrow(
                ()->new NotFoundCustomException("InKind case with id : "+id+" not found!")
        );
    }
}
