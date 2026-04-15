package com.moviesapi.movies_api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviesapi.movies_api.model.Actor;
import com.moviesapi.movies_api.service.ActorService;

/**
 * REST Controller for managing Actor entities.
 * Provides endpoints for CRUD operations and filtering.
 */
@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    /**
     * Retrieves all actors or filters them by name.
     * @param name Optional search query for actor name.
     * @return A list of matching actors.
     */
    @GetMapping
    public List<Actor> getAllActors(@RequestParam(required = false) String name) {
        if (name != null) {
            return actorService.searchActorsByName(name);
        }
        return actorService.getAllActors();
    }

    /**
     * Retrieves a single actor by their unique ID.
     * @param id The ID of the actor.
     * @return ResponseEntity containing the actor data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable Long id) {
        return ResponseEntity.ok(actorService.getActorById(id));
    }

    /**
     * Creates a new actor record.
     * @param actor The actor object to be saved.
     * @return The created actor with 201 Created status.
     */
    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor) {
        return new ResponseEntity<>(actorService.saveActor(actor), HttpStatus.CREATED);
    }

    /**
     * Partially updates an existing actor's information.
     * @param id The ID of the actor to update.
     * @param updates A map containing the fields to be updated (e.g., name, birthDate).
     * @return The updated actor entity.
     */
    @PatchMapping("/{id}")
    public ResponseEntity<Actor> updateActor(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(actorService.patchActor(id, updates));
    }

    /**
     * Deletes an actor from the database.
     * @param id The ID of the actor to delete.
     * @param force If true, removes all movie associations before deleting. 
     * If false, deletion will fail if the actor is linked to movies.
     * @return 204 No Content on success, or 400 Bad Request on failure.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(
            @PathVariable Long id, 
            @RequestParam(defaultValue = "false") boolean force) {
        try {
            actorService.deleteActor(id, force);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}