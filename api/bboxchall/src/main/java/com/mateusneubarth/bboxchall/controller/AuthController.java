package com.mateusneubarth.bboxchall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateusneubarth.bboxchall.dto.request.LoginRequest;
import com.mateusneubarth.bboxchall.dto.request.RegistrationRequest;
import com.mateusneubarth.bboxchall.model.UserModel;
import com.mateusneubarth.bboxchall.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
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
        UserModel user = authService.authenticate(
            request.getUsername(),
            request.getPassword()
        );
        return ResponseEntity.ok(user);
    }
}