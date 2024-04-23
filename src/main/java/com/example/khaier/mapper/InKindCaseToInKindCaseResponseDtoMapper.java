package com.example.khaier.mapper;

import com.example.khaier.dto.response.InKindCaseResponseDto;
import com.example.khaier.entity.InKindCase;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class InKindCaseToInKindCaseResponseDtoMapper implements Function<InKindCase, InKindCaseResponseDto> {

    @Override
    public InKindCaseResponseDto apply(InKindCase inKindCase) {
        return InKindCaseResponseDto
                .builder()
                .inKindCaseId(inKindCase.getId())
                .title(inKindCase.getTitle())
                .includedItemName(inKindCase.getIncludedItemName())
                .build();
    }
}
