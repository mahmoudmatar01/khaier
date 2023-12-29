package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.UserLoginRequestDto;
import com.example.khaier.dto.request.UserRegistrationRequestDto;
import com.example.khaier.dto.response.UserRegisterResponseDto;
import com.example.khaier.entity.user.User;
import com.example.khaier.exceptions.PasswordMismatchException;
import com.example.khaier.exceptions.EmailAlreadyExistException;
import com.example.khaier.mapper.AdminRegisterDtoToAdmin;
import com.example.khaier.mapper.UserRegisterDtoToUserMapper;
import com.example.khaier.mapper.UserToUserResponseDtoMapper;
import com.example.khaier.repository.user.UserRepository;
import com.example.khaier.security.JwtTokenUtils;
import com.example.khaier.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
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
    private final AdminRegisterDtoToAdmin adminRegisterDtoToAdmin;

    @Override
    public UserRegisterResponseDto registerUser(UserRegistrationRequestDto registerRequest) {
        checkUser(registerRequest);
        User user = userRegisterDtoToUserMapper.apply(registerRequest);
        userRepository.save(user);
        return userResponseDtoMapper.apply(user);
    }

    @Override
    public UserRegisterResponseDto registerAdmin(UserRegistrationRequestDto adminDto) {
        checkUser(adminDto);
        User user = adminRegisterDtoToAdmin.apply(adminDto);
        userRepository.save(user);
        return userResponseDtoMapper.apply(user);
    }
    @Override
    public String loginUser(UserLoginRequestDto loginRequest) {
        User user = userRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> new NotFoundException("User not found with email: " + loginRequest.email()));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String jwtToken = jwtTokenUtils.generateToken(user, "loginToken");
        user.setAccessToken(jwtToken);
        userRepository.save(user);

        return user.getAccessToken();
    }

    private void checkUser(UserRegistrationRequestDto adminDto) {
        if (userRepository.existsByEmail(adminDto.email())) {
            throw new EmailAlreadyExistException("Email address already exists.");
        }
        if(!adminDto.password().equals(adminDto.confirmPassword())){
            throw new PasswordMismatchException("The passwords entered don't match. Please try again.");
        }
    }

}

