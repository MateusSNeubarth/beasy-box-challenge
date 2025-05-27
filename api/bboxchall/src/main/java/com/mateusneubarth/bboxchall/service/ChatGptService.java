package com.mateusneubarth.bboxchall.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatGptService {

    private final ChatClient chatClient;

    public ChatGptService(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    public String getResponse(String prompt) {
        return chatClient
                .prompt(prompt)
                .call()
                .content();
    }
}
