package com.cinemasystemspring.dto;


import lombok.Data;

@Data
public class CreateSeatRequestDTO {
    private String rowIdentifier;
    private Integer seatNumber;
}
