package com.cinemasystemspring.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CreatePurchaseRequestDTO {

    private Long userId;
    private Long movieSessionId;
    private Set<Long> seatIds;
}
