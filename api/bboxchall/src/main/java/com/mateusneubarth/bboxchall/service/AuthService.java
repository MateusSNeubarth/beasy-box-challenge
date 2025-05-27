package com.mateusneubarth.bboxchall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mateusneubarth.bboxchall.model.UserModel;
import com.mateusneubarth.bboxchall.repository.UserRepository;
import com.mateusneubarth.bboxchall.security.JwtTokenProvider;
import com.mateusneubarth.bboxchall.security.UserPrincipal;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
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
        // Autenticação via Spring Security
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        
        // Obter o usuário autenticado
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        // Retornar o UserModel (entidade JPA)
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}