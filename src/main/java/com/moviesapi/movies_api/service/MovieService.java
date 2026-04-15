package com.moviesapi.movies_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviesapi.movies_api.model.Movie;
import com.moviesapi.movies_api.repository.MovieRepository;

/**
 * Service class for managing Movie business logic.
 * Provides various filtering methods for movies based on release year, genres, actors, or titles.
 */
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Retrieves all movies from the database.
     */
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    /**
     * Finds a single movie by its ID.
     * @throws RuntimeException if no movie is found with the given ID.
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
     * @param genreId ID of the genre to filter by.
     */
    public List<Movie> getMoviesByGenre(Long genreId) {
        return movieRepository.findByGenresId(genreId);
    }

    /**
     * Retrieves movies featuring a specific actor.
     * @param actorId ID of the actor to filter by.
     */
    public List<Movie> getMoviesByActor(Long actorId) {
        return movieRepository.findByActorsId(actorId);
    }

    /**
     * Searches for movies with titles containing the search string (case-insensitive).
     * @param title Keyword to search in titles.
     */
    public List<Movie> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
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