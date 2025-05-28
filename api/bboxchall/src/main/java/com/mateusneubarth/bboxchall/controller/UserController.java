package com.mateusneubarth.bboxchall.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateusneubarth.bboxchall.model.UserModel;
import com.mateusneubarth.bboxchall.service.UserService;
import com.mateusneubarth.bboxchall.utils.LoginRequest;
import com.mateusneubarth.bboxchall.utils.RegistrationRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody RegistrationRequest request) {
        UserModel existingUser = userService.getUserByUsername(request.getUsername());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        UserModel user = userService.register(request.getUsername(), request.getEmail(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User registration failed");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/login")
    public ResponseEntity getUserByEmail(@RequestBody LoginRequest request) {
        try {
            userService.getUserByUsername(request.getUsername());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        UserModel user = userService.login(request.getUsername(), request.getPassword());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}