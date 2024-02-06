package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.InKindDonationRequestDto;
import com.example.khaier.dto.response.InKindDonationResponseDto;
import com.example.khaier.entity.InKindCase;
import com.example.khaier.entity.InKindDonation;
import com.example.khaier.exceptions.NotFoundCustomException;
import com.example.khaier.repository.InKindCasesRepository;
import com.example.khaier.repository.InKindDonationRepository;
import com.example.khaier.repository.OrganizationsRepository;
import com.example.khaier.repository.UserRepository;
import com.example.khaier.service.InKindDonationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InKindDonationsServiceImpl implements InKindDonationsService {

    private final InKindCasesRepository inKindCasesRepository;
    private final UserRepository usersRepository;
    private final OrganizationsRepository organizationsRepository;
    private final InKindDonationRepository inKindDonationRepository;

    @Override
    public List<InKindCase> getAllInKindDonationCases() {
        return inKindCasesRepository.findAll();
    }

    @Override
    public InKindDonationResponseDto addDonation(InKindDonationRequestDto requestDto) {
        var user = usersRepository.findById(requestDto.userId())
                .orElseThrow(() -> new NotFoundCustomException("There is no User with id : " + requestDto.userId()));

        var organization = organizationsRepository.findById(requestDto.organizationId())
                .orElseThrow(() -> new NotFoundCustomException("There is no Organization with id : " + requestDto.organizationId()));

        var inKindCase = inKindCasesRepository.findById(requestDto.inKindCaseId()).
                orElseThrow(() -> new NotFoundCustomException("There is no InKind Case with id : " + requestDto.inKindCaseId()));


        var itemName = inKindCase.getIncludedItemName() == null
                ? requestDto.itemName()
                : inKindCase.getIncludedItemName();

        var donation = new InKindDonation(user,
                requestDto.phone(),
                organization,
                inKindCase,
                requestDto.itemAmount(),
                itemName,
                requestDto.lang(),
                requestDto.lat(),
                requestDto.addressDescription());
        donation = inKindDonationRepository.save(donation);

        return new InKindDonationResponseDto(
                donation.getDonationId(),
                user.getUserId(),
                user.getUsername(),
                donation.getPhone(),
                organization.getOrgId(),
                organization.getOrgName(),
                inKindCase.getId(),
                inKindCase.getTitle(),
                donation.getItemAmount(),
                donation.getItemName(),
                donation.getDonationTime(),
                donation.getLang(),
                donation.getLat(),
                donation.getAddressDescription()
        );
    }

}
