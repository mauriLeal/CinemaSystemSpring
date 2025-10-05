package com.cinemasystemspring.controller;

import com.cinemasystemspring.dto.CreateSessionRequestDTO;
import com.cinemasystemspring.dto.MovieSessionDTO;
import com.cinemasystemspring.service.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies/{movieId}/sessions")
public class MovieSessionController {

    @Autowired
    private MovieSessionService sessionService;


    @PostMapping
    public ResponseEntity<MovieSessionDTO> createSessionForMovie(@PathVariable Long movieId, @RequestBody CreateSessionRequestDTO requestDTO) {

        MovieSessionDTO createdSessionDTO = sessionService.createSession(movieId, requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSessionDTO);

    }

    @GetMapping
    public ResponseEntity<List<MovieSessionDTO>> getAllSessionsForMovie(@PathVariable Long movieId){
        List<MovieSessionDTO> sessions = sessionService.findAllSessionsForMovie(movieId);
        return ResponseEntity.ok(sessions);

    }

    @PutMapping("/{sessionId}")
    public ResponseEntity<MovieSessionDTO> updateSession(
            @PathVariable Long movieId,
            @PathVariable Long sessionId,
            @RequestBody CreateSessionRequestDTO requestDTO) {

        MovieSessionDTO updatedSession = sessionService.updateSession(movieId, sessionId, requestDTO);
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long movieId, @PathVariable Long sessionId){
        sessionService.deleteSession(movieId, sessionId);

        return ResponseEntity.noContent().build();
    }


}
