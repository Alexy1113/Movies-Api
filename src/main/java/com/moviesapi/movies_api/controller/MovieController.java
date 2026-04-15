package com.moviesapi.movies_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviesapi.movies_api.model.Movie;
import com.moviesapi.movies_api.service.MovieService;

/**
 * REST Controller for managing Movie entities.
 * Handles primary operations including complex filtering by year, genre, actor, or title.
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * Retrieves movies based on various optional filters.
     * Priority is given to the first non-null parameter provided.
     * * @param year  Filter movies by release year.
     * @param genre Filter movies by genre ID.
     * @param actor Filter movies by actor ID.
     * @param title Search movies by a title keyword.
     * @return A list of movies matching the criteria, or all movies if no filters are applied.
     */
    @GetMapping
    public List<Movie> getMovies(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false) Long actor,
            @RequestParam(required = false) String title) {
        
        if (year != null) return movieService.getMoviesByYear(year);
        if (genre != null) return movieService.getMoviesByGenre(genre);
        if (actor != null) return movieService.getMoviesByActor(actor);
        if (title != null) return movieService.searchByTitle(title);
        
        return movieService.getAllMovies();
    }

    /**
     * Retrieves a single movie by its unique ID.
     * @param id The ID of the movie.
     * @return ResponseEntity containing the movie data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    /**
     * Creates and saves a new movie entry.
     * Associations with genres and actors should be provided as lists of IDs.
     * * @param movie The movie object to be created.
     * @return The saved movie entity with 201 Created status.
     */
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.CREATED);
    }

    /**
     * Permanently deletes a movie from the database.
     * @param id The ID of the movie to remove.
     * @return 204 No Content on successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}