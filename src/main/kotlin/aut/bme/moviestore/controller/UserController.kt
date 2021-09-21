package aut.bme.moviestore.controller

import aut.bme.moviestore.data.dto.response.ResponseDTO
import aut.bme.moviestore.service.MovieService
import aut.bme.moviestore.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService, private val movieService: MovieService) {

    private val logger: Logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping("/register")
    fun register(
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "email") email: String,
        @RequestParam(value = "password") password: String
    ): ResponseEntity<ResponseDTO> {
        logger.info("Registering user.")
        return userService.register(name, email, password)
    }

    @GetMapping("/login")
    fun login(
        @RequestParam(value = "email") email: String,
        @RequestParam(value = "password") password: String
    ): ResponseEntity<ResponseDTO> {
        logger.info("Logging in with email/password.")
        return userService.login(email, password)
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value = ["/{id}"])
    fun deleteUser(@PathVariable id: String): ResponseEntity<ResponseDTO> {
        logger.info("Deleting user with id: {}", id)
        return userService.deleteUser(id)
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}/favorites")
    fun getFavoriteMovies(
        @PathVariable id: String
    ): ResponseEntity<ResponseDTO> {
        return movieService.getFavoriteMovies(id)
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/{id}")
    fun addMovieToFavorites(
        @PathVariable id: String,
        @RequestParam(value = "movieId") movieId: String
    ): ResponseEntity<ResponseDTO> {
        return movieService.addMovieToFavorites(id, movieId)
    }
}