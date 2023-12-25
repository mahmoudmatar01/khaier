package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.UserLoginDto;
import com.example.khaier.dto.request.UserRegistrationDto;
import com.example.khaier.entity.user.User;
import com.example.khaier.entity.user.UserImage;
import com.example.khaier.exceptions.MisMatchException;
import com.example.khaier.exceptions.NotFoundAuthenticatedUserException;
import com.example.khaier.mapper.UserRegisterDtoToUserMapper;
import com.example.khaier.repository.user.UserRepository;
import com.example.khaier.security.JwtTokenUtils;
import com.example.khaier.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserRegisterDtoToUserMapper userRegisterDtoToUserMapper;
    private final UserImageServiceImpl imageService;

    public User registerUser(UserRegistrationDto registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new NotFoundAuthenticatedUserException("Email address already exists.");
        }

        if(!registerRequest.password().equals(registerRequest.confirmPassword())){
            throw new MisMatchException("The passwords entered don't match. Please try again.");
        }

        // Create a new user
        User user = userRegisterDtoToUserMapper.apply(registerRequest);
        return userRepository.save(user);

    }

    public String loginUser(UserLoginDto loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + loginRequest.email()));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return jwtTokenUtils.generateToken(user, "loginToken");
    }
}
