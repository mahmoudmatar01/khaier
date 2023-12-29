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

@Component
@RequiredArgsConstructor
public class AdminRegisterDtoToAdmin implements Function<UserRegistrationRequestDto, User> {
    private final PasswordEncoder passwordEncoder;
    private final UserImageServiceImpl userImageService;
    @Override
    public User apply(UserRegistrationRequestDto adminRequestDto) {
        try{
            UserImage userImage = userImageService.uploadImage(adminRequestDto.image());
            return User.builder()
                    .email(adminRequestDto.email())
                    .username(adminRequestDto.username())
                    .userRole(Role.ROLE_ADMIN)
                    .userGender(adminRequestDto.userGender())
                    .password(passwordEncoder.encode(adminRequestDto.password()))
                    .phone(adminRequestDto.userPhone())
                    .userImage(userImage)
                    .userImageUrl(userImage.getUrl())
                    .build();
        }
        catch (IOException exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
}
