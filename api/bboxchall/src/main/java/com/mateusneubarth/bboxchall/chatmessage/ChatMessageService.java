package com.mateusneubarth.bboxchall.chatmessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessageService {

    @Autowired
    private IChatMessageRepository chatMessageRepository;

    public void saveMessage(String message, String sender, String receiver) {
        ChatMessageModel chatMessage = new ChatMessageModel();
        chatMessage.setMessage(message);
        chatMessage.setSender(sender);
        chatMessage.setReceiver(receiver);
        chatMessageRepository.save(chatMessage);
    }
}
