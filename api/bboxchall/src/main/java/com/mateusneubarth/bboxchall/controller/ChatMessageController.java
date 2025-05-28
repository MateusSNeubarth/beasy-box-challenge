package com.mateusneubarth.bboxchall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mateusneubarth.bboxchall.model.ChatMessageModel;
import com.mateusneubarth.bboxchall.model.UserModel;
import com.mateusneubarth.bboxchall.service.ChatMessageService;
import com.mateusneubarth.bboxchall.service.UserService;

@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/send")
    public String sendMessage(@RequestParam String username, 
                             @RequestParam String message) {
        UserModel user = userService.getUserByUsername(username);
        return chatService.processMessage(user, message);
    }

    @GetMapping("/history")
    public List<ChatMessageModel> getChatHistory(@RequestParam String username) {
        UserModel user = userService.getUserByUsername(username);
        return chatService.getChatHistory(user);
    }
}