package com.cinemasystemspring.repository;

import com.cinemasystemspring.model.Movie;
import com.cinemasystemspring.model.MovieSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieSessionRepository extends JpaRepository<MovieSession, Long> {


    List<MovieSession> findByMovieId(Long movieId);

}

