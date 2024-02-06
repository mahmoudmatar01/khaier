package com.example.khaier.service.Impl;

import com.example.khaier.entity.InKindCase;
import com.example.khaier.repository.InKindCasesRepository;
import com.example.khaier.service.InKindDonationsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InKindDonationsServiceImpl implements InKindDonationsService {

    private final InKindCasesRepository inKindCasesRepository;

    @Override
    public List<InKindCase> getAllInKindDonationCases() {
        return inKindCasesRepository.findAll();
    }
}
