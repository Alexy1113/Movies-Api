package com.moviesapi.movies_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviesapi.movies_api.model.Genre;

/**
 * Repository interface for Genre entity.
 * Inherits standard CRUD (Create, Read, Update, Delete) operations from JpaRepository.
 * This interface allows the application to interact with the 'genres' table in the database.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    // No custom methods required as JpaRepository provides all necessary basic functionality.
}