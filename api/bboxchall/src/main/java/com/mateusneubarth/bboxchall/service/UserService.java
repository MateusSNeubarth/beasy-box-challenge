package com.mateusneubarth.bboxchall.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.mateusneubarth.bboxchall.model.UserModel;
import com.mateusneubarth.bboxchall.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel register(String username, String email, String password) {
        UserModel user = new UserModel();
        String encryptedPassword = BCrypt
            .hashpw(password, BCrypt.gensalt());
        user.setPassword(encryptedPassword);
        user.setUsername(username);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public UserModel login(String email, String password) {
        UserModel user = userRepository.findByUsername(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public UserModel getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}