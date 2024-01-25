package com.example.khaier.helper;

import com.example.khaier.dto.request.UserRegistrationRequestDto;
import com.example.khaier.entity.User;
import com.example.khaier.exceptions.EmailAlreadyExistException;
import com.example.khaier.exceptions.PasswordMismatchException;
import com.example.khaier.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
@RequiredArgsConstructor
public class UserHelper {

    private final UserRepository userRepository;
    public User findUserByIdOrThrowNotFoundException(Long userId){
        return userRepository.findById(userId).orElseThrow(
                ()-> new NotFoundException("user with id : "+userId+" not found!")
        );
    }
    public User findUserByEmailOrThrowNotFoundException(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }

    public void checkUserExistAndPasswordEqualAfterRegister(UserRegistrationRequestDto adminDto) {
        if (userRepository.existsByEmail(adminDto.email())) {
            throw new EmailAlreadyExistException("Email address already exists.");
        }
        if(!adminDto.password().equals(adminDto.confirmPassword())){
            throw new PasswordMismatchException("The passwords entered don't match. Please try again.");
        }
    }
}
