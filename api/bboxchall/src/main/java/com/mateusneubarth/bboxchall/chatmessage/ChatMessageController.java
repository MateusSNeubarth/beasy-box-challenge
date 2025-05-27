package com.mateusneubarth.bboxchall.chatmessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatMessageController {

    @Autowired
    private ChatMessageService chatMessageService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/chat/message")
    public ResponseEntity saveMessage(@RequestBody ChatMessageModel chatMessage) {
        chatMessageService.saveMessage(chatMessage.getMessage(), chatMessage.getSender(), chatMessage.getReceiver());
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Message saved successfully");
    }
}
