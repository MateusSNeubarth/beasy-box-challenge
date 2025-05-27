package com.mateusneubarth.bboxchall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGptService {
    @Value("${chatgpt.api.key}")
    private String apiKey;
    
    @Value("${chatgpt.api.url}")
    private String apiUrl;
    
    public String getResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(Map.of("role", "user", "content", prompt)));
        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> response = restTemplate.postForEntity(
            apiUrl,
            entity,
            Map.class
        );
        
        // Processar a resposta (simplificado)
        Map<String, Object> responseBody = response.getBody();
        // Extrair a resposta do ChatGPT do responseBody
        
        return "Resposta do ChatGPT"; // Substituir pela lógica real
    }
}