package com.example.khaier.service.Impl;

import com.example.khaier.dto.request.UserLoginRequestDto;
import com.example.khaier.dto.request.UserRegistrationRequestDto;
import com.example.khaier.dto.response.UserRegisterResponseDto;
import com.example.khaier.entity.User;
import com.example.khaier.enums.Role;
import com.example.khaier.helper.UserHelper;
import com.example.khaier.mapper.UserRegisterDtoToUserMapper;
import com.example.khaier.mapper.UserToUserResponseDtoMapper;
import com.example.khaier.repository.UserRepository;
import com.example.khaier.security.JwtTokenUtils;
import com.example.khaier.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserHelper userHelper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserRegisterDtoToUserMapper userRegisterDtoToUserMapper;
    private final UserToUserResponseDtoMapper userResponseDtoMapper;

    @Override
    public UserRegisterResponseDto registerUser(UserRegistrationRequestDto registerRequest) {
        userHelper.checkUserExistAndPasswordEqualAfterRegister(registerRequest);
        User user = userRegisterDtoToUserMapper.apply(registerRequest);
        userRepository.save(user);
        return userResponseDtoMapper.apply(user);
    }

    @Override
    public UserRegisterResponseDto registerAdmin(UserRegistrationRequestDto adminDto) {
        userHelper.checkUserExistAndPasswordEqualAfterRegister(adminDto);
        User user = userRegisterDtoToUserMapper.apply(adminDto, Role.ROLE_ADMIN);
        userRepository.save(user);
        return userResponseDtoMapper.apply(user);
    }
    @Override
    public String loginUser(UserLoginRequestDto loginRequest) {
        User user = userHelper.checkUserIsExistOrByEmailThrowException(loginRequest.email());
        checkPasswordsMatch(loginRequest.password(), user.getPassword());
        String jwtToken = jwtTokenUtils.generateToken(user);
        user.setAccessToken(jwtToken);
        user=userRepository.save(user);
        return user.getAccessToken();
    }

  private void checkPasswordsMatch(String pass1,String pass2){
      if (!passwordEncoder.matches(pass1, pass2)) {
          throw new BadCredentialsException("Invalid password");
      }
  }

}

