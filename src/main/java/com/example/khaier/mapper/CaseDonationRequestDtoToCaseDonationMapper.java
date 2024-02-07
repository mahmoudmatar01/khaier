package com.example.khaier.mapper;

import com.example.khaier.dto.request.CaseDonationRequestDto;
import com.example.khaier.entity.CaseDonation;
import com.example.khaier.entity.DonationCategoryCase;
import com.example.khaier.exceptions.NotFoundCustomException;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.repository.DonationCategoryCaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CaseDonationRequestDtoToCaseDonationMapper implements Function<CaseDonationRequestDto, CaseDonation> {
    private final DonationCategoryCaseRepository donationCaseRepository;
    private final UserHelper userHelper;
    @Override
    public CaseDonation apply(CaseDonationRequestDto caseDonationRequestDto) {
        DonationCategoryCase categoryCase=findCaseByIdOrThrowException(caseDonationRequestDto.caseId());
        return CaseDonation.builder()
                .user(userHelper.findUserByIdOrThrowNotFoundException(caseDonationRequestDto.userId()))
                .donationCase(categoryCase)
                .organization(categoryCase.getDonationCategory().getCharitableOrganization())
                .amount(caseDonationRequestDto.amount())
                .donationWay(caseDonationRequestDto.donationWay())
                .donationTime(LocalDateTime.now())
                .build();
    }

    private DonationCategoryCase findCaseByIdOrThrowException(Long caseId){
        return donationCaseRepository.findById(caseId).orElseThrow(
                ()->new NotFoundCustomException("Case with id : "+caseId+" not found!")
        );
    }
}

