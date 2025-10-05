package com.cinemasystemspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDTO {
    private long id;
    private String title;
    private String director;
    private Integer durationInMinutes;

    private List<MovieSessionDTO> sessions;
}
