package com.example.khaier.helper;

import com.example.khaier.entity.user.User;
import com.example.khaier.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

@Component
@RequiredArgsConstructor
public class UserHelper {

    private final UserRepository userRepository;
    public User checkUserIsExistOrThrowException(Long userId){
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new NotFoundException("user with id : "+userId+" not found!")
        );
        return user;
    }

}
