package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.InKindCaseRequestDto;
import com.example.khaier.dto.response.InKindCaseResponseDto;
import com.example.khaier.entity.InKindCase;
import com.example.khaier.mapper.InKindCaseRequestDtoToInKindCaseMapper;
import com.example.khaier.mapper.InKindCaseToInKindCaseResponseDtoMapper;
import com.example.khaier.repository.InKindCasesRepository;
import com.example.khaier.service.InKindCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InKindCaseServiceImpl implements InKindCaseService {
    private final InKindCaseRequestDtoToInKindCaseMapper toInKindCaseMapper;
    private final InKindCaseToInKindCaseResponseDtoMapper toInKindCaseResponseDtoMapper;
    private final InKindCasesRepository repository;
    @Override
    public List<InKindCaseResponseDto> findAllInKindCase() {
        return repository.findAll().stream().map(toInKindCaseResponseDtoMapper).toList();
    }

    @Override
    public InKindCaseResponseDto saveInKindCase(InKindCaseRequestDto requestDto) {
        InKindCase inKindCase=toInKindCaseMapper.apply(requestDto);
        inKindCase=repository.save(inKindCase);
        return toInKindCaseResponseDtoMapper.apply(inKindCase);
    }
}
