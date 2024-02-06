package com.example.khaier.helper;

import com.example.khaier.entity.CharitableOrganization;
import com.example.khaier.exceptions.NotFoundCustomException;
import com.example.khaier.repository.CharityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharityOrgHelper {
    private final CharityRepository charityRepository;

    public CharitableOrganization findCharityByIdOrThrowNotFound(Long charityId){
        return charityRepository.findById(charityId).orElseThrow(
                ()->new NotFoundCustomException("Charity with id : "+charityId+" not found!")
        );
    }
}
