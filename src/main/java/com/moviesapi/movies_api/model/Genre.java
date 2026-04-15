package com.moviesapi.movies_api.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a movie genre entity in the database (e.g., Sci-Fi, Drama).
 * A genre can be associated with multiple movies.
 */
@Entity
@Table(name = "genres")
@Getter @Setter @NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Genre name cannot be empty")
    @Column(unique = true)
    private String name;

    /**
     * Many-to-Many relationship with Movie entity.
     * Mapped by the 'genres' field in the Movie class.
     * @JsonIgnore is used to prevent infinite recursion during JSON serialization.
     */
    @ManyToMany(mappedBy = "genres")
    @JsonIgnore
    private Set<Movie> movies;
}