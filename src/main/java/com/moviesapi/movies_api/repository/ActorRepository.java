package com.moviesapi.movies_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviesapi.movies_api.model.Actor;

/**
 * Repository interface for Actor entity.
 * Provides standard CRUD operations and custom search functionality using Spring Data JPA.
 */
@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    /**
     * Finds actors whose names contain the given string, ignoring case.
     * Spring Data JPA automatically generates the SQL "LIKE" query.
     * * @param name The string to search for within actor names.
     * @return A list of actors that match the search criteria.
     */
    List<Actor> findByNameContainingIgnoreCase(String name);
}