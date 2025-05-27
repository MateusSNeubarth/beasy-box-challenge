package com.mateusneubarth.bboxchall.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mateusneubarth.bboxchall.model.ChatMessageModel;
import com.mateusneubarth.bboxchall.model.UserModel;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageModel, UUID> {
    List<ChatMessageModel> findByUserOrderByTimestampAsc(UserModel user);
}
