package com.cinemasystemspring.service;


import com.cinemasystemspring.dto.CreateSeatRequestDTO;
import com.cinemasystemspring.dto.SeatDTO;
import com.cinemasystemspring.model.Seat;
import com.cinemasystemspring.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public SeatDTO createSeat(CreateSeatRequestDTO requestDTO){
        Seat newSeat = new Seat();
        newSeat.setRowIdentifier(requestDTO.getRowIdentifier());
        newSeat.setSeatNumber(requestDTO.getSeatNumber());
        Seat savedSeat = seatRepository.save(newSeat);
        return convertToDTO(savedSeat);
    }

    @Transactional(readOnly = true)
    public SeatDTO findSeatById(Long id){
        Seat seat = findSeatEntityById(id);
        return convertToDTO(seat);
    }

    public Seat findSeatEntityById(Long id){
        return seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assento não encontrado com o id " + id));
    }
    @Transactional(readOnly = true)
    public List<SeatDTO> findAllSeats(){
        return seatRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private SeatDTO convertToDTO(Seat seat){
        SeatDTO dto = new SeatDTO();
        dto.setId(seat.getId());
        dto.setRowIdentifier(seat.getRowIdentifier());
        dto.setSeatNumber(seat.getSeatNumber());
        return dto;
    }

}
