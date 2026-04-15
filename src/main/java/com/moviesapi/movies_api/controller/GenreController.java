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

import com.moviesapi.movies_api.model.Genre;
import com.moviesapi.movies_api.service.GenreService;

/**
 * REST Controller for managing Movie Genres.
 * Handles requests for creating, retrieving, and deleting genre data.
 */
@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    /**
     * Retrieves a list of all available genres in the database.
     * @return List of Genre entities.
     */
    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }

    /**
     * Retrieves details of a specific genre by its ID.
     * @param id The unique identifier of the genre.
     * @return ResponseEntity containing the Genre object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    /**
     * Adds a new genre to the database.
     * @param genre The genre object to create.
     * @return The created genre with 201 Created status.
     */
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.saveGenre(genre), HttpStatus.CREATED);
    }

    /**
     * Deletes a genre from the system.
     * Includes protection against deleting genres that are currently linked to movies.
     * * @param id The ID of the genre to delete.
     * @param force If true, bypasses the check and removes links to movies before deletion.
     * If false, returns 400 Bad Request if the genre is in use.
     * @return 204 No Content on success, or 400 Bad Request with an error message.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(
            @PathVariable Long id, 
            @RequestParam(required = false, defaultValue = "false") boolean force) {
        try {
            genreService.deleteGenre(id, force);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Returns the error message (e.g., "Cannot delete genre...") to the client
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}