package com.mateusneubarth.bboxchall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateusneubarth.bboxchall.dto.request.ChatMessageRequest;
import com.mateusneubarth.bboxchall.model.ChatMessageModel;
import com.mateusneubarth.bboxchall.model.UserModel;
import com.mateusneubarth.bboxchall.service.ChatGptService;
import com.mateusneubarth.bboxchall.service.ChatMessageService;


@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {
    @Autowired
    private ChatMessageService chatMessageService;

    private final ChatGptService chatGptService;

    public ChatMessageController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }
    
    @PostMapping("/message")
    public ResponseEntity<ChatMessageModel> sendMessage(
            @RequestBody ChatMessageRequest request,
            @AuthenticationPrincipal UserModel user) {
        
        ChatMessageModel message = chatMessageService.saveMessage(
            request.getContent(),
            true, // is user message
            user
        );
        
        String chatGptResponse = chatGptService.getResponse(request.getContent());
        chatMessageService.saveMessage(chatGptResponse, false, user);
        
        return ResponseEntity.ok(message);
    }
    
    @GetMapping("/history")
    public ResponseEntity<List<ChatMessageModel>> getHistory(@AuthenticationPrincipal UserModel user) {
        return ResponseEntity.ok(chatMessageService.getMessagesByUser(user));
    }
}