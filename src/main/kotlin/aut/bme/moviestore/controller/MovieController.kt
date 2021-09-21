package aut.bme.moviestore.controller

import aut.bme.moviestore.data.dto.response.ResponseDTO
import aut.bme.moviestore.service.MovieService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/movies")
class MovieController(private val movieService: MovieService) {

    private val logger: Logger = LoggerFactory.getLogger(MovieController::class.java)

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/title")
    fun getMoviesByTitle(
        @RequestParam(value = "title") title: String,
    ): ResponseEntity<ResponseDTO> {
        return movieService.getMoviesByTitle(title)
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/releaseDate")
    fun getMoviesByReleaseDate(
        @RequestParam(value = "releaseDate") releaseDate: String
    ): ResponseEntity<ResponseDTO> {
        return movieService.getMoviesByReleaseDate(releaseDate)
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/director")
    fun getMoviesByDirector(
        @RequestParam(value = "director") director: String
    ): ResponseEntity<ResponseDTO> {
        return movieService.getMoviesByDirector(director)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id: String): ResponseEntity<ResponseDTO> {
        logger.info("Deleting user with id: {}", id)
        return movieService.deleteMovie(id)
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    fun addMovie(
        @RequestParam(value = "title") title: String,
        @RequestParam(value = "director") director: String,
        @RequestParam(value = "release_date") releaseDate: String
    ): ResponseEntity<ResponseDTO> {
        logger.info("Adding movie.")
        return movieService.addMovie(title, director, releaseDate)
    }
}