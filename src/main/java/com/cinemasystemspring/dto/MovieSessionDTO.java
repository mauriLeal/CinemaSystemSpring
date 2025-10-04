package com.cinemasystemspring.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovieSessionDTO {

    private long id;
    private LocalDateTime sessionTime;
    private String screenNumber;

    private String movieTitle;
    private String movieDirector;
    private Integer movieDurationInMinutes;
}
