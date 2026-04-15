# 🎬 Movies API
A simple and efficient REST API for managing a movie database. Built with Spring Boot and SQLite, this project allows you to manage movies, actors, and genres with complex relationships.

# 🚀 Features
Movies: Create, read, and delete movies.

Actors & Genres: Full management with Many-to-Many relationships.

Smart Filtering: Search movies by title, year, genre, or specific actor.

Safe Deletion: Protects genres and actors from being deleted if they are linked to movies (with a force delete option).

# 🛠️ Tech Stack
* Java 17/21
* Spring Boot 3.x
* Spring Data JPA (Hibernate)
* SQLite

# 📂 Project Structure
`model/` — Database entities (Movie, Actor, Genre).

`repository/` — Interfaces for database operations.

`service/` — Business logic (filtering, validation, patching).

`controller/` — REST API endpoints for Postman/Frontend.

# 🚦 How to Run
Open the project in VS Code or IntelliJ IDEA.

Ensure you have the Extension Pack for Java installed.

Run the MoviesApiApplication.java file.

The server will start at: http://localhost:8080

# 📡 API Endpoints (Quick Start)
## 🎬 Movies
GET `/api/movies` — Get all movies.

GET `/api/movies?year=2010` — Filter by year.

GET `/api/movies?actor=1` — Find all movies for a specific actor ID.

POST `/api/movies` — Add a new movie.

## 🎭 Actors
GET `/api/actors` — Get all actors.

POST `/api/actors` — Create a new actor.

PATCH `/api/actors/{id}` — Update actor details.

## 🏷️ Genres
GET `/api/genres` — Get all genres.

Deleting:
* Normal Delete:
 DELETE `/api/genres/{id}:`

* Force delete a genre even if linked to movies:
DELETE `/api/genres/{id}?force=true`

# 🧪 Example Request (POST Movie)
URL: http://localhost:8080/api/movies

Body (JSON):
```json
JSON
{
  "title": "Inception",
  "releaseYear": 2010,
  "duration": 148,
  "genres": [{"id": 1}],
  "actors": [{"id": 1}]
}
``` 

# 💡 Note on Database
This project uses a local SQLite file. The data is saved automatically in the project folder, so your movies won't disappear after a restart!