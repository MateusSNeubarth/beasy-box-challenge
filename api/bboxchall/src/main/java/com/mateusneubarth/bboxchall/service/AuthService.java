package com.mateusneubarth.bboxchall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mateusneubarth.bboxchall.model.UserModel;
import com.mateusneubarth.bboxchall.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserModel registerUser(String username, String email, String password) {
        if (userRepository.findByUsername(username).isPresent() || 
            userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Username or email already exists");
        }
        
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        
        return userRepository.save(user);
    }
    
    public UserModel authenticate(String username, String password) {
        UserModel user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        return user;
    }
}