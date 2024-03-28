package com.example.khaier.controller;

import com.example.khaier.dto.request.ChangePasswordRequestDto;
import com.example.khaier.dto.response.UserInfoResponseDto;
import com.example.khaier.factory.impl.SuccessResponseFactory200;
import com.example.khaier.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/${api.version}/user")
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

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto){
        userService.changePassword(changePasswordRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseFactory.createResponse(null,"Password changed successfully "));
    }
}
