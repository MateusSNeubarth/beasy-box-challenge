package com.mateusneubarth.bboxchall.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "chat_message")
public class ChatMessageModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;
    
    @Column(nullable = false)
    private boolean isUserMessage;
    
    @Column(nullable = false)
    private LocalDateTime timestamp;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;
}
