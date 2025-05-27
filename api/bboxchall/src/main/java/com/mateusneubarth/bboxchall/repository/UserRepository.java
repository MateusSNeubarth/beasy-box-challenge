package com.mateusneubarth.bboxchall.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateusneubarth.bboxchall.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);
    Optional<UserModel> findByEmail(String email);
}
