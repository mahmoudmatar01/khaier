package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.UserLoginDto;
import com.example.khaier.dto.request.UserRegistrationDto;
import com.example.khaier.dto.response.UserResponseDto;
import com.example.khaier.entity.user.User;
import com.example.khaier.exceptions.PasswordMismatchException;
import com.example.khaier.exceptions.EmailAlreadyExistException;
import com.example.khaier.mapper.UserRegisterDtoToUserMapper;
import com.example.khaier.mapper.UserToUserResponseDtoMapper;
import com.example.khaier.repository.user.UserRepository;
import com.example.khaier.security.JwtTokenUtils;
import com.example.khaier.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserRegisterDtoToUserMapper userRegisterDtoToUserMapper;
    private final UserToUserResponseDtoMapper userResponseDtoMapper;

    public UserResponseDto registerUser(UserRegistrationDto registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new EmailAlreadyExistException("Email address already exists.");
        }
        if(!registerRequest.password().equals(registerRequest.confirmPassword())){
            throw new PasswordMismatchException("The passwords entered don't match. Please try again.");
        }
        User user = userRegisterDtoToUserMapper.apply(registerRequest);
        userRepository.save(user);
        return userResponseDtoMapper.apply(user);
    }

    public String loginUser(UserLoginDto loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + loginRequest.email()));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return jwtTokenUtils.generateToken(user, "loginToken");
    }
}
