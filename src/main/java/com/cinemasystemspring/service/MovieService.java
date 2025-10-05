package com.cinemasystemspring.service;

import com.cinemasystemspring.dto.CreateMovieRequestDTO;
import com.cinemasystemspring.dto.MovieDTO;
import com.cinemasystemspring.dto.MovieSessionDTO;
import com.cinemasystemspring.dto.UpdateMovieRequestDTO;
import com.cinemasystemspring.model.Movie;
import com.cinemasystemspring.repository.MovieRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;


    public MovieDTO createMovie(CreateMovieRequestDTO requestDTO){

        Movie movie = new Movie();
        movie.setTitle(requestDTO.getTitle());
        movie.setDirector(requestDTO.getDirector());
        movie.setDurationInMinutes(requestDTO.getDurationInMinutes());

        Movie savedMovie = movieRepository.save(movie);
        return convertToDTO(savedMovie);

    }


    public MovieDTO updateMovie(Long id, UpdateMovieRequestDTO requestDTO){
        Movie existingMovie = findMovieEntityById(id);

        if (requestDTO.getTitle() != null){
            existingMovie.setTitle(requestDTO.getTitle());
        }
        if (requestDTO.getDirector() != null){
            existingMovie.setDirector(requestDTO.getDirector());
        }
        if (requestDTO.getDurationInMinutes() != null){
            existingMovie.setDurationInMinutes(requestDTO.getDurationInMinutes());
        }

        Movie updatedMovie = movieRepository.save(existingMovie);
        System.out.println("TESTE" + updatedMovie);
        return convertToDTO(updatedMovie);
    }


    public void deleteMovie(Long id){

        findByMovieId(id);
        movieRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<MovieDTO> findAllMovies(){
        return movieRepository.findAll()
                .stream()
                .map(this::convertToDTO) // converte cada filme da lista para dto.
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MovieDTO findByMovieId(Long id){
        Movie movie = findMovieEntityById(id);
        return convertToDTO(movie);
    }

    @Transactional(readOnly = true)
    public Movie findMovieEntityById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com o id " + id));
    }

    private MovieDTO convertToDTO(Movie movie){
        List<MovieSessionDTO> sessionDTOs = movie.getSessions() != null ?
                movie.getSessions()
                    .stream()
                    .map(session -> {
                        MovieSessionDTO sessionDTO = new MovieSessionDTO();
                        sessionDTO.setId(session.getId());
                        sessionDTO.setSessionTime(session.getSessionTime());
                        sessionDTO.setScreenNumber(session.getScreenNumber());

                        sessionDTO.setMovieTitle(movie.getTitle());
                        sessionDTO.setMovieDirector(movie.getDirector());
                        sessionDTO.setMovieDurationInMinutes(movie.getDurationInMinutes());

                        return sessionDTO;
                })
                .collect(Collectors.toList()) : List.of();

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDirector(movie.getDirector());
        movieDTO.setDurationInMinutes(movie.getDurationInMinutes());
        movieDTO.setSessions(sessionDTOs);

        return movieDTO;
    }

}
