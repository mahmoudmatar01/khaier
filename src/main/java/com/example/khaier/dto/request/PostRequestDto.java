package com.example.khaier.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record PostRequestDto(
        @NotBlank MultipartFile[]image,
        @NotBlank String postContent
) {
}
