package com.mateusneubarth.bboxchall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateusneubarth.bboxchall.dto.request.LoginRequest;
import com.mateusneubarth.bboxchall.dto.request.RegistrationRequest;
import com.mateusneubarth.bboxchall.exception.ResourceNotFoundException;
import com.mateusneubarth.bboxchall.model.UserModel;
import com.mateusneubarth.bboxchall.repository.UserRepository;
import com.mateusneubarth.bboxchall.security.JwtTokenProvider;
import com.mateusneubarth.bboxchall.security.UserPrincipal;
import com.mateusneubarth.bboxchall.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody RegistrationRequest request) {
        UserModel user = authService.registerUser(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody LoginRequest request) {
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        UserModel user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(user);
    }
}
