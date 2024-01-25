package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.ChangePasswordRequestDto;
import com.example.khaier.dto.response.UserInfoResponseDto;
import com.example.khaier.entity.User;
import com.example.khaier.exceptions.BadRequestException;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.repository.UserRepository;
import com.example.khaier.security.JwtTokenUtils;
import com.example.khaier.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserHelper userHelper;
    @Override
    public UserInfoResponseDto extractUserFromToken(String token) {
        if(!jwtTokenUtils.validateToken(token)){
            throw new BadRequestException("Invalid token!");
        }
        return jwtTokenUtils.extractUserFromToken(token);
    }

    @Override
    public void changePassword(ChangePasswordRequestDto changePasswordRequestDto) {
        User user=userHelper.findUserByEmailOrThrowNotFoundException(changePasswordRequestDto.email());
        if(!changePasswordRequestDto.newPassword().equals(changePasswordRequestDto.confirmationPassword())){
            throw new BadCredentialsException("Passwords are not the same!");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequestDto.newPassword()));
        userRepository.save(user);
    }
}
