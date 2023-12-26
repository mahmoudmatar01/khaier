package com.example.khaier.controller;

import com.example.khaier.dto.response.UserInfoResponseDto;
import com.example.khaier.entity.user.User;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.Impl.AuthServiceImpl;
import com.example.khaier.service.Impl.UserImageServiceImpl;
import com.example.khaier.service.Impl.UserServiceImpl;
import com.example.khaier.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SuccessResponseFactory200 responseFactory;

    @GetMapping("/info")
    public ResponseEntity<?> getUserFromToken(@RequestHeader("Authorization") String token){
        String jwtToken = token.substring(7);
        UserInfoResponseDto user = userService.extractUserFromToken(jwtToken);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(user,"User returned successfully "));
    }
}
