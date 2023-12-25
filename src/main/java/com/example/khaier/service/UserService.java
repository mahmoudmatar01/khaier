package com.example.khaier.service;

import com.example.khaier.entity.user.User;

public interface UserService {
    User extractUserFromToken(String token);
}
