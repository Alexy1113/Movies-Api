package com.moviesapi.movies_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moviesapi.movies_api.model.Genre;
import com.moviesapi.movies_api.repository.GenreRepository;

/**
 * Service class for managing movie genres.
 * Provides functionality for lifecycle management and integrity checks during deletion.
 */
@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    /**
     * Retrieves all genres available in the database.
     */
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    /**
     * Finds a specific genre by its unique ID.
     * @throws RuntimeException if the genre does not exist.
     */
    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found with ID: " + id));
    }

    /**
     * Creates or updates a genre record.
     */
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    /**
     * Deletes a genre with integrity validation.
     * @param id The ID of the genre to delete.
     * @param force If false, checks if movies are still assigned to this genre.
     * If true, clears associations before performing the delete.
     */
    @Transactional
    public void deleteGenre(Long id, boolean force) {
        Genre genre = getGenreById(id);
        
        // Prevent accidental deletion of genres that are currently in use
        if (!genre.getMovies().isEmpty() && !force) {
            throw new RuntimeException("Cannot delete genre '" + genre.getName() + 
                "' because it has " + genre.getMovies().size() + " associated movies. Use 'force=true' to override.");
        }
        
        if (force) {
            // Break the Many-to-Many relationship in the join table
            genre.getMovies().forEach(movie -> movie.getGenres().remove(genre));
        }
        
        genreRepository.delete(genre);
    }
}