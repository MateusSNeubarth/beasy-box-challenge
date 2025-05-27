package com.mateusneubarth.bboxchall.service;


import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mateusneubarth.bboxchall.dto.response.UserProfileResponse;
import com.mateusneubarth.bboxchall.exception.ResourceNotFoundException;
import com.mateusneubarth.bboxchall.model.UserModel;
import com.mateusneubarth.bboxchall.repository.UserRepository;
import com.mateusneubarth.bboxchall.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username)
            .orElseThrow(() -> 
                new UsernameNotFoundException("Usuário não encontrado: " + username));
        
        return UserPrincipal.create(user);
    }
    
    public UserProfileResponse getUserProfile(UUID userId) {
        Optional<UserModel> user = userRepository.findById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }
        return mapToUserProfileResponse(user);
    }
    
    private UserProfileResponse mapToUserProfileResponse(Optional<UserModel> user) {
        UserProfileResponse response = new UserProfileResponse();
        UserModel userModel = user.orElseThrow(() -> new ResourceNotFoundException("Usuário", "id", null));
        response.setId(userModel.getId());
        response.setUsername(userModel.getUsername());
        response.setEmail(userModel.getEmail());
        return response;
    }
    
    public void updateLastLogin(UUID userId) {
        Optional<UserModel> user = userRepository.findById(userId);
        if (user == null) {
            throw new ResourceNotFoundException("Usuário", "id", userId);
        }
    }
}