package com.cinemasystemspring.dto;

import lombok.Data;

@Data
public class SeatDTO {
    private Long id;
    private String rowIdentifier;
    private Integer seatNumber;
}
