package com.cinemasystemspring.repository;

import com.cinemasystemspring.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
