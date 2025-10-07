package com.cinemasystemspring.dto;


import lombok.Data;

@Data
public class UpdateMovieRequestDTO {

    private String title;
    private String director;
    private Integer durationInMinutes;

}
