package com.moviesapi.movies_api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moviesapi.movies_api.model.Actor;
import com.moviesapi.movies_api.repository.ActorRepository;

/**
 * Service class for managing business logic related to Actors.
 * Handles data validation, partial updates, and safe deletion protocols.
 */
@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    /**
     * Retrieves a list of all actors from the database.
     */
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    /**
     * Finds a specific actor by ID.
     * @throws RuntimeException if the actor is not found.
     */
    public Actor getActorById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor with ID " + id + " not found"));
    }

    /**
     * Searches for actors by name using a case-insensitive keyword.
     */
    public List<Actor> searchActorsByName(String name) {
        return actorRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Persists a new or updated actor entity.
     */
    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    /**
     * Deletes an actor with a safety check.
     * @param id The ID of the actor to delete.
     * @param force If false, prevents deletion if the actor is linked to movies.
     * If true, clears associations first and then deletes.
     */
    @Transactional
    public void deleteActor(Long id, boolean force) {
        Actor actor = getActorById(id);

        // Check if actor is linked to movies before performing a standard delete
        if (!actor.getMovies().isEmpty() && !force) {
            throw new RuntimeException("Unable to delete actor '" + actor.getName() + 
                "' as they are associated with " + actor.getMovies().size() + " movies.");
        }

        if (force) {
            // Break Many-to-Many links in the join table before deletion
            actor.getMovies().forEach(movie -> movie.getActors().remove(actor));
        }

        actorRepository.delete(actor);
    }

    /**
     * Performs a partial update (PATCH) on an actor entity.
     * @param id The ID of the actor to update.
     * @param updates A map of fields and their new values.
     * @return The updated actor entity.
     */
    @Transactional
    public Actor patchActor(Long id, Map<String, Object> updates) {
        Actor actor = getActorById(id);
        
        // Dynamically update fields based on the request body
        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> actor.setName((String) value);
                case "birthDate" -> actor.setBirthDate(java.time.LocalDate.parse((String) value));
            }
        });
        
        return actorRepository.save(actor);
    }
}