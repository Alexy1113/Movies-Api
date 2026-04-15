package com.moviesapi.movies_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviesapi.movies_api.model.Movie;

/**
 * Repository interface for Movie entity.
 * Provides abstraction for database operations and custom query methods 
 * for filtering movies by year, genre, actor, or title keyword.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    /**
     * Retrieves a list of movies released in a specific year.
     * @param year The release year to filter by.
     */
    List<Movie> findByReleaseYear(Integer year);

    /**
     * Retrieves movies associated with a specific genre.
     * Use this to find all movies within a category (e.g., all "Sci-Fi" movies).
     * @param genreId The unique ID of the genre.
     */
    List<Movie> findByGenresId(Long genreId);

    /**
     * Retrieves movies featuring a specific actor.
     * @param actorId The unique ID of the actor.
     */
    List<Movie> findByActorsId(Long actorId);

    /**
     * Performs a case-insensitive search for movies whose titles contain the given string.
     * Useful for implementing a search bar in the UI.
     * @param title The keyword to search for in movie titles.
     */
    List<Movie> findByTitleContainingIgnoreCase(String title);
}