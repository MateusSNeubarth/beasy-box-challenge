package com.mateusneubarth.bboxchall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateusneubarth.bboxchall.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}