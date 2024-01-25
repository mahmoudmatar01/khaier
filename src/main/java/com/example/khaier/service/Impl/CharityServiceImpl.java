package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.CharityRequestDto;
import com.example.khaier.dto.response.CharityResponseDto;
import com.example.khaier.entity.CharitableOrganization;
import com.example.khaier.entity.User;
import com.example.khaier.enums.Role;
import com.example.khaier.exceptions.BadRequestException;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.CharityRequestDtoToCharityMapper;
import com.example.khaier.mapper.CharityToCharityResponseDtoMapper;
import com.example.khaier.repository.CharityRepository;
import com.example.khaier.service.CharityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CharityServiceImpl implements CharityService {
    private final CharityRepository charityRepository;
    private final CharityToCharityResponseDtoMapper toCharityResponseDtoMapper;
    private final CharityRequestDtoToCharityMapper toCharityMapper;
    private final UserHelper userHelper;
    @Override
    public CharityResponseDto saveCharity(CharityRequestDto requestDto,Long adminId) {
        User user = userHelper.findUserByIdOrThrowNotFoundException(adminId);
        if(user.getUserRole() != Role.ROLE_ADMIN)
            throw new BadRequestException("User not authorized to get this service");
        CharitableOrganization charity=toCharityMapper.apply(requestDto);
        charity=charityRepository.save(charity);
        return toCharityResponseDtoMapper.apply(charity);
    }

    @Override
    public List<CharityResponseDto> getAllCharity() {
        List<CharitableOrganization>charities=charityRepository.findAll();
        return charities.stream().map(toCharityResponseDtoMapper::apply).toList();
    }
}
