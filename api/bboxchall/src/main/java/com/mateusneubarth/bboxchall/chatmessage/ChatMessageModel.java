package com.mateusneubarth.bboxchall.chatmessage;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "chat_message")
public class ChatMessageModel {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private UUID id;
    private String message;
    private String sender;
    private String receiver;
}
