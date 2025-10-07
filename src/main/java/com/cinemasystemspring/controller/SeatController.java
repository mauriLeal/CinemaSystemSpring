package com.cinemasystemspring.controller;


import com.cinemasystemspring.dto.CreateSeatRequestDTO;
import com.cinemasystemspring.dto.SeatDTO;
import com.cinemasystemspring.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping
    public ResponseEntity<SeatDTO> createSeat(@RequestBody CreateSeatRequestDTO requestDTO){
        SeatDTO createdSeat = seatService.createSeat(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSeat);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatDTO> getSeatById(@PathVariable Long id){
        SeatDTO seat = seatService.findSeatById(id);
        return ResponseEntity.ok(seat);
    }

    @GetMapping
    public ResponseEntity<List<SeatDTO>> getAllSeats(){
        List<SeatDTO> seats = seatService.findAllSeats();
        return ResponseEntity.ok(seats);
    }

}
