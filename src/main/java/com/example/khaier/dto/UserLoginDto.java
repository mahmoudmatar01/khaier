package com.example.khaier.dto;

import lombok.Data;

@Data
public record UserLoginDto(
         String email,
         String password
) {

}
