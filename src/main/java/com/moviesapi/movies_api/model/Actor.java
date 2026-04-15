package com.moviesapi.movies_api.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents an Actor entity in the database.
 * Stores personal details of actors and their associations with movies.
 */
@Entity
@Table(name = "actors")
@Getter @Setter @NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Actor name is mandatory")
    private String name;

    @NotNull(message = "Birth date is mandatory")
    private LocalDate birthDate;

    /**
     * Many-to-Many relationship with the Movie entity.
     * Mapped by the 'actors' field in the Movie class.
     * @JsonIgnore prevents circular references during JSON serialization.
     */
    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    private Set<Movie> movies;
}