package com.example.khaier.dto.request;

import lombok.Builder;


@Builder
public record ReplyRequestDto(
        Long commentId,
        String content
) {
}
