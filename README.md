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
* Jackson (FasterXML)

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

# 📄Pagination Support
To handle large datasets efficiently, all GET endpoints that return multiple entities now support Pagination. This prevents the server from being overloaded and provides a better user experience for frontend applications.

Base Endpoint: `GET /api/movies`

Query Parameters:

* page: The page index (starts from 0).

* size: Number of records per page (default: 10).

Example Request: GET `/api/movies?page=0&size=5`

Page Object Structure:
The API now returns a structured Page object instead of a simple array:

* content: The list of movie/actor objects for the requested page.

* totalElements: Total number of records in the database.

* totalPages: Total number of pages available based on the size parameter.

* last / first: Boolean flags to help with UI navigation (Previous/Next buttons).

# 🔍 Advanced Search with Pagination
The search functionality has been upgraded to support both keyword filtering and pagination simultaneously.

Endpoint: GET `/api/movies/search`

Example: `/api/movies/search?title=the&page=0&size=5`

Behavior: Returns all movies containing the keyword "the" in their title, sliced into pages of 5 items.

# 💡 Optimized JSON Response Ordering
To improve data readability in API testing tools like Postman (especially in Table View), the JSON serialization order has been strictly defined using Jackson annotations.

* Movie Table: title is now the primary column.

* Actor Sub-table: name is displayed first.

# 💡 Note on Database
This project uses a local SQLite file. The data is saved automatically in the project folder, so your movies won't disappear after a restart!