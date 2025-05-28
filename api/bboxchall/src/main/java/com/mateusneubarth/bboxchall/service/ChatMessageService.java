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
    ChatMessageRepository chatMessageRepository;

    @Autowired
    private AiService aiService;

    private ChatMessageModel saveUserMessage(UserModel user, String message) {
        return saveMessage(user, message, true);
    }

    private ChatMessageModel saveAiResponse(UserModel user, String message) {
        return saveMessage(user, message, false);
    }

    private ChatMessageModel saveMessage(UserModel user, String content, boolean isUserMessage) {
        ChatMessageModel chatMessage = new ChatMessageModel();
        chatMessage.setContent(content);
        chatMessage.setUserMessage(isUserMessage);
        chatMessage.setTimestamp(LocalDateTime.now());
        chatMessage.setUser(user);
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessageModel> getChatHistory(UserModel user) {
        return chatMessageRepository.findByUserOrderByTimestampAsc(user);
    }

    public String processMessage(UserModel user, String message) {
        saveUserMessage(user, message);
        String response = aiService.chat(message);
        saveAiResponse(user, response);
        return response;
    }
}
