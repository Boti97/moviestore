package aut.bme.moviestore.controller

import aut.bme.moviestore.data.dto.response.MovieDetailsResponseDTO
import aut.bme.moviestore.data.dto.response.StringResponseDTO
import aut.bme.moviestore.service.MovieService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/movie")
class MovieController(private val movieService: MovieService) {

    private val logger: Logger = LoggerFactory.getLogger(MovieController::class.java)

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/title")
    fun getMoviesByTitle(
        @RequestParam(value = "title") title: String,
    ): ResponseEntity<List<MovieDetailsResponseDTO>> {
        return movieService.getMoviesByTitle(title)
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/releaseDate")
    fun getMoviesByReleaseDate(
        @RequestParam(value = "releaseDate") releaseDate: String
    ): ResponseEntity<List<MovieDetailsResponseDTO>> {
        return movieService.getMoviesByReleaseDate(LocalDate.parse(releaseDate))
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/director")
    fun getMoviesByDirector(
        @RequestParam(value = "director") director: String
    ): ResponseEntity<List<MovieDetailsResponseDTO>> {
        return movieService.getMoviesByDirector(director)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = ["/{id}"])
    fun deleteMovie(@PathVariable id: String): ResponseEntity<StringResponseDTO> {
        logger.info("Deleting user with id: {}", id)
        return movieService.deleteMovie(id)
    }
}