package com.cinemasystemspring.service;


import com.cinemasystemspring.dto.CreateMovieRequestDTO;
import com.cinemasystemspring.dto.CreateSessionRequestDTO;
import com.cinemasystemspring.dto.MovieSessionDTO;
import com.cinemasystemspring.model.Movie;
import com.cinemasystemspring.model.MovieSession;
import com.cinemasystemspring.repository.MovieSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieSessionService {


    @Autowired
    private MovieSessionRepository sessionRepository;

    @Autowired
    private MovieService movieService;

    public MovieSessionDTO createSession(Long movieId, CreateSessionRequestDTO requestDTO){

        Movie movie = movieService.findMovieEntityById(movieId);

        MovieSession newSession = new MovieSession();
        newSession.setSessionTime(requestDTO.getSessionTime());
        newSession.setScreenNumber(requestDTO.getScreenNumber());
        newSession.setMovie(movie);


        MovieSession savedSession = sessionRepository.save(newSession);

        return convertToDTO(savedSession);
    }

    public MovieSessionDTO updateSession(
            @PathVariable Long movieId,
            @PathVariable Long sessionId,
            @RequestBody CreateSessionRequestDTO requestDTO) {

        MovieSession existingSession = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada com o id: " + sessionId));

        if (!existingSession.getMovie().getId().equals(movieId)) {
            throw new RuntimeException("Conflito: A sessão " + sessionId + "não pertence ao Filme " + movieId);
        }

        if (requestDTO.getSessionTime() != null) {
            existingSession.setSessionTime(requestDTO.getSessionTime());
        }
        if (requestDTO.getScreenNumber() != null) {
            existingSession.setScreenNumber(requestDTO.getScreenNumber());
        }

        MovieSession updatedSession = sessionRepository.save(existingSession);

        return convertToDTO(updatedSession);
    }




    public void deleteSession(Long movieId, Long sessionId) {
        MovieSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada com o id: " + sessionId));

        if (!session.getMovie().getId().equals(movieId)){
            throw new RuntimeException("Conflito: A sessão " + sessionId + " não pertence ao filme " + movieId);
        }

        sessionRepository.delete(session);
    }

    public List<MovieSessionDTO> findAllSessionsForMovie(Long movieId){
        movieService.findMovieEntityById(movieId);

        return sessionRepository.findByMovieId(movieId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MovieSessionDTO convertToDTO(MovieSession session) {
        MovieSessionDTO dto = new MovieSessionDTO();
        dto.setId(session.getId());
        dto.setSessionTime(session.getSessionTime());
        dto.setScreenNumber(session.getScreenNumber());
        dto.setMovieTitle(session.getMovie().getTitle());
        dto.setMovieDirector(session.getMovie().getDirector());
        dto.setMovieDurationInMinutes(session.getMovie().getDurationInMinutes());
        return dto;

    }

}

