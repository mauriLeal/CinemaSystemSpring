package com.cinemasystemspring.service;


import com.cinemasystemspring.dto.CreateMovieRequestDTO;
import com.cinemasystemspring.dto.CreateSessionRequestDTO;
import com.cinemasystemspring.dto.MovieSessionDTO;
import com.cinemasystemspring.model.Movie;
import com.cinemasystemspring.model.MovieSession;
import com.cinemasystemspring.repository.MovieSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<MovieSessionDTO> findAllSessionsForMovie(Long movieId){
        movieService.findMovieEntityById(movieId);

        return sessionRepository.findMovieById(movieId)
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
