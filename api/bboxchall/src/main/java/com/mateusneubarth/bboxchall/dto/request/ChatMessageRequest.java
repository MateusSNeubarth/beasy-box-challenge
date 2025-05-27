package com.mateusneubarth.bboxchall.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChatMessageRequest {
    @NotBlank(message = "O conteúdo da mensagem não pode estar vazio")
    private String content;
}