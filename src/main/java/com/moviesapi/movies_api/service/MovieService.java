package com.moviesapi.movies_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable; 
import org.springframework.stereotype.Service;

import com.moviesapi.movies_api.model.Movie;
import com.moviesapi.movies_api.repository.MovieRepository;

/**
 * Service class for managing Movie business logic.
 * Handles CRUD operations, filtering, and paginated searching.
 */
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Retrieves all movies without pagination.
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Retrieves a paginated list of all movies.
     * Useful for large databases to limit response size.
     */
    public Page<Movie> getAllMoviesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable);
    }

    /**
     * Finds a single movie by its ID.
     */
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + id));
    }

    /**
     * Filters movies by their release year.
     */
    public List<Movie> getMoviesByYear(Integer year) {
        return movieRepository.findByReleaseYear(year);
    }

    /**
     * Retrieves movies that belong to a specific genre.
     */
    public List<Movie> getMoviesByGenre(Long genreId) {
        return movieRepository.findByGenresId(genreId);
    }

    /**
     * Retrieves movies featuring a specific actor.
     */
    public List<Movie> getMoviesByActor(Long actorId) {
        return movieRepository.findByActorsId(actorId);
    }

    /**
     * Performs a paginated search for movies by title (case-insensitive).
     * @param title Keyword to search.
     * @param page Current page index.
     * @param size Number of records per page.
     */
    public Page<Movie> searchByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    /**
     * Saves a new movie or updates an existing one.
     */
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * Deletes a movie from the database by its ID.
     */
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}