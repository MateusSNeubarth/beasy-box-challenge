package com.mateusneubarth.bboxchall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mateusneubarth.bboxchall.model.ChatMessageModel;
import com.mateusneubarth.bboxchall.model.UserModel;

public interface ChatMessageRepository extends JpaRepository<ChatMessageModel, Long> {
    List<ChatMessageModel> findByUserOrderByTimestampAsc(UserModel user);
}