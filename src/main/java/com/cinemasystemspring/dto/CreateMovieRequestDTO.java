package com.cinemasystemspring.dto;


import lombok.Data;

@Data
public class CreateMovieRequestDTO {

    private String title;
    private String director;
    private Integer durationInMinutes;
}
