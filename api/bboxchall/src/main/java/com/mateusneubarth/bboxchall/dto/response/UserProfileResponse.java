package com.mateusneubarth.bboxchall.dto.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserProfileResponse {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
