package com.example.khaier.mapper;
import com.example.khaier.dto.request.InKindCaseRequestDto;
import com.example.khaier.entity.InKindCase;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.function.Function;

@Component
public class InKindCaseRequestDtoToInKindCaseMapper implements Function<InKindCaseRequestDto, InKindCase> {
    @Override
    public InKindCase apply(InKindCaseRequestDto inKindCaseRequestDto) {
        return InKindCase.builder()
                .title(inKindCaseRequestDto.title())
                .includedItemName(inKindCaseRequestDto.includedItemName())
                .donations(new ArrayList<>())
                .build();
    }
}
