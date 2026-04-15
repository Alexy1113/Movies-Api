package com.moviesapi.movies_api.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a Movie entity in the database.
 * Each movie has a title, release year, duration, and associated genres and actors.
 */
@Entity
@Table(name = "movies")
@Getter @Setter @NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Movie title is mandatory")
    private String title;

    @NotNull(message = "Release year is mandatory")
    private Integer releaseYear;

    private Integer duration; // Duration in minutes

    /**
     * Defines a Many-to-Many relationship with the Genre entity.
     * Creates a join table named 'movie_genre'.
     */
    @ManyToMany
    @JoinTable(
        name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres;

    /**
     * Defines a Many-to-Many relationship with the Actor entity.
     * Creates a join table named 'movie_actor'.
     */
    @ManyToMany
    @JoinTable(
        name = "movie_actor",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private Set<Actor> actors;
}