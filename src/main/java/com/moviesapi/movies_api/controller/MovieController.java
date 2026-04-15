package com.moviesapi.movies_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
 */
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * Extra requirements: Paginated search by title.
     * GET /api/movies/search?title=matrix&page=0&size=5
     */
    @GetMapping("/search")
    public ResponseEntity<Page<Movie>> searchMovies(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(movieService.searchByTitle(title, page, size));
    }

    /**
     * Extra requirements: Paginated list of all movies.
     * GET /api/movies/paginated?page=0&size=10
     */
    @GetMapping("/paginated")
    public ResponseEntity<Page<Movie>> getMoviesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(movieService.getAllMoviesPaginated(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    /**
     * Requirement: Get all actors in a specific movie.
     * GET /api/movies/{movieId}/actors
     */
   /**
     * Retrieves movies with support for filtering AND pagination.
     * Use case: GET /api/movies?page=0&size=10
     */
    @GetMapping
    public ResponseEntity<Page<Movie>> getMovies(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Long genre,
            @RequestParam(required = false) Long actor,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        
        // If no filters are specified, all movies are returned with pagination.
        if (year == null && genre == null && actor == null) {
            return ResponseEntity.ok(movieService.getAllMoviesPaginated(page, size));
        }

       // If there are filters, we return them (for simplicity, in this task, filters return a List)
        //  But to preserve the Page structure, we can wrap them in PageImpl
        List<Movie> results;
        if (year != null) results = movieService.getMoviesByYear(year);
        else if (genre != null) results = movieService.getMoviesByGenre(genre);
        else results = movieService.getMoviesByActor(actor);

        return ResponseEntity.ok(new org.springframework.data.domain.PageImpl<>(results, pageable, results.size()));
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}