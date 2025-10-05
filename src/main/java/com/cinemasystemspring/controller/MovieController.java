package com.cinemasystemspring.controller;

import com.cinemasystemspring.dto.CreateMovieRequestDTO;
import com.cinemasystemspring.dto.MovieDTO;
import com.cinemasystemspring.dto.UpdateMovieRequestDTO;
import com.cinemasystemspring.model.Movie;
import com.cinemasystemspring.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieDTO> getAllMovies(){
        return movieService.findAllMovies();
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id){
        MovieDTO movieDTO = movieService.findByMovieId(id);
        return ResponseEntity.ok(movieDTO);
    }


    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody CreateMovieRequestDTO movieRequest){
        MovieDTO createdMovieDTO = movieService.createMovie(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovieDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody UpdateMovieRequestDTO movieRequest){
        MovieDTO updateMovie = movieService.updateMovie(id, movieRequest);
        return ResponseEntity.ok(updateMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){

        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }



}
