package com.mateusneubarth.bboxchall.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mateusneubarth.bboxchall.model.ChatMessageModel;
import com.mateusneubarth.bboxchall.model.UserModel;
import com.mateusneubarth.bboxchall.repository.ChatMessageRepository;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    
    public ChatMessageModel saveMessage(String content, boolean isUserMessage, UserModel user) {
        ChatMessageModel message = new ChatMessageModel();
        message.setContent(content);
        message.setUserMessage(isUserMessage);
        message.setTimestamp(LocalDateTime.now());
        message.setUser(user);
        
        return chatMessageRepository.save(message);
    }
    
    public List<ChatMessageModel> getMessagesByUser(UserModel user) {
        return chatMessageRepository.findByUserOrderByTimestampAsc(user);
    }
}