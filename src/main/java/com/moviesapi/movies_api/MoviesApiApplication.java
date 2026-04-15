package com.moviesapi.movies_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Movies API application.
 * This class initializes the Spring Boot framework, starts the embedded 
 * web server, and scans for components in the project.
 */

@SpringBootApplication
public class MoviesApiApplication {

	/**
     * The main method that launches the application.
     * @param args command-line arguments (not used in this project).
     */

	public static void main(String[] args) {
		SpringApplication.run(MoviesApiApplication.class, args);
	}

}
