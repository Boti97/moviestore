package aut.bme.moviestore.service

import aut.bme.moviestore.data.dto.response.MovieDetailsResponseDTO
import aut.bme.moviestore.data.dto.response.StringResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.List.of


@Component
class MovieService {

    fun getMoviesByTitle(title: String): ResponseEntity<List<MovieDetailsResponseDTO>> {
        val response = of(MovieDetailsResponseDTO("id", "title", LocalDate.MAX, "director"))
        return ResponseEntity(response, HttpStatus.OK)
    }

    fun getMoviesByReleaseDate(releaseDate: LocalDate): ResponseEntity<List<MovieDetailsResponseDTO>> {
        val response = of(MovieDetailsResponseDTO("id", "title", LocalDate.MAX, "director"))
        return ResponseEntity(response, HttpStatus.OK)
    }

    fun getMoviesByDirector(director: String): ResponseEntity<List<MovieDetailsResponseDTO>> {
        val response = of(MovieDetailsResponseDTO("id", "title", LocalDate.MAX, "director"))
        return ResponseEntity(response, HttpStatus.OK)
    }

    fun addMovieToFavorites(userId: String, movieID: String): ResponseEntity<StringResponseDTO> {
        val reponseMessage = StringResponseDTO("")
        return ResponseEntity(reponseMessage, HttpStatus.OK)
    }

    fun getFavoriteMovies(userId: String): ResponseEntity<List<MovieDetailsResponseDTO>> {
        val response = of(MovieDetailsResponseDTO("id", "title", LocalDate.MAX, "director"))
        return ResponseEntity(response, HttpStatus.OK)
    }

    fun deleteMovie(id: String): ResponseEntity<StringResponseDTO> {
        val reponseMessage = StringResponseDTO("")
        return ResponseEntity(reponseMessage, HttpStatus.OK)
    }
}