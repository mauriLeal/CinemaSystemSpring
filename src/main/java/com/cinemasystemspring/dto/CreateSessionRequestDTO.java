package com.cinemasystemspring.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateSessionRequestDTO {

    private LocalDateTime sessionTime;
    private String screenNumber;
}
