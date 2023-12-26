package com.example.khaier.mapper;

import com.example.khaier.dto.request.UserRegistrationRequestDto;
import com.example.khaier.entity.user.User;
import com.example.khaier.entity.user.UserImage;
import com.example.khaier.enums.Role;
import com.example.khaier.service.Impl.UserImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class UserRegisterDtoToUserMapper implements Function<UserRegistrationRequestDto, User> {
    private final PasswordEncoder passwordEncoder;
    private final UserImageServiceImpl userImageService;
    @Override
    public User apply(UserRegistrationRequestDto userRegistrationDto) {
        try{
            UserImage userImage = userImageService.uploadImage(userRegistrationDto.imageUrl());
            return User.builder()
                    .email(userRegistrationDto.email())
                    .username(userRegistrationDto.username())
                    .userRole(Role.ROLE_USER)
                    .userGender(userRegistrationDto.userGender())
                    .password(passwordEncoder.encode(userRegistrationDto.password()))
                    .phone(userRegistrationDto.userPhone())
                    .userImage(userImage)
                    .userImageUrl(userImage.getUrl())
                    .build();
        }
        catch (IOException exception){
            throw new RuntimeException(exception.getMessage());
        }

    }
}
