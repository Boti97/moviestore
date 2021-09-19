package aut.bme.moviestore.controller

import aut.bme.moviestore.data.dto.response.MovieDetailsResponseDTO
import aut.bme.moviestore.data.dto.response.StringResponseDTO
import aut.bme.moviestore.service.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class MovieController(private val movieService: MovieService) {

    @GetMapping("/title")
    fun getMoviesByTitle(
        @RequestParam(value = "title") title: String,
    ): ResponseEntity<List<MovieDetailsResponseDTO>> {
        return movieService.getMoviesByTitle(title)
    }

    @GetMapping("/releaseDate")
    fun getMoviesByReleaseDate(
        @RequestParam(value = "releaseDate") releaseDate: String
    ): ResponseEntity<List<MovieDetailsResponseDTO>> {
        return movieService.getMoviesByReleaseDate(LocalDate.parse(releaseDate))
    }

    @GetMapping("/director")
    fun getMoviesByDirector(
        @RequestParam(value = "director") director: String
    ): ResponseEntity<List<MovieDetailsResponseDTO>> {
        return movieService.getMoviesByDirector(director)
    }

    @PostMapping("/")
    fun addMovieToFavorites(
        @RequestParam(value = "userId") userId: String,
        @RequestParam(value = "movieId") movieId: String
    ): ResponseEntity<StringResponseDTO> {
        return movieService.addMovieToFavorites(userId, movieId)
    }

    @GetMapping("{id}/movies")
    fun getFavoriteMovies(
        @RequestParam(value = "userId") userId: String
    ): ResponseEntity<List<MovieDetailsResponseDTO>> {
        return movieService.getFavoriteMovies(userId)
    }
}