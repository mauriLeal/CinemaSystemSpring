package com.cinemasystemspring.service;

import com.cinemasystemspring.model.Movie;
import com.cinemasystemspring.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAllMovies(){
        return movieRepository.findAll();
    }

    public Movie findMovieById(Long id){
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado com o id " + id));
    }

    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, Movie movieDetails){
        Movie existingMovie = findMovieById(id);

        if (movieDetails.getTitle() != null){
            existingMovie.setTitle(movieDetails.getTitle());
        }

        if (movieDetails.getDirector() != null){
            existingMovie.setDirector(movieDetails.getDirector());
        }

        if (movieDetails.getDurationInMinutes() != null){
            existingMovie.setDurationInMinutes(movieDetails.getDurationInMinutes());
        }

        return movieRepository.save(existingMovie);
    }

    public void deleteMovie(Long id){

        findMovieById(id);

        movieRepository.deleteById(id);
    }
}
