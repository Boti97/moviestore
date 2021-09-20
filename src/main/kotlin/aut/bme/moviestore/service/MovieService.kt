package aut.bme.moviestore.service

import aut.bme.moviestore.data.dto.response.MovieDetailsResponseDTO
import aut.bme.moviestore.data.dto.response.StringResponseDTO
import aut.bme.moviestore.data.repository.MovieRepository
import aut.bme.moviestore.data.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.time.LocalDate
import kotlin.streams.toList


@Component
class MovieService(private val movieRepository: MovieRepository, private val userRepository: UserRepository) {

    fun getMoviesByTitle(title: String): ResponseEntity<List<MovieDetailsResponseDTO>> {
        val response =
            movieRepository.findAllByTitle(title).stream().map { MovieDetailsResponseDTO.createFromMovie(it) }.toList()
        return ResponseEntity(response, HttpStatus.OK)
    }

    fun getMoviesByReleaseDate(releaseDate: LocalDate): ResponseEntity<List<MovieDetailsResponseDTO>> {
        val response =
            movieRepository.findAllByReleaseDate(releaseDate).stream()
                .map { MovieDetailsResponseDTO.createFromMovie(it) }.toList()
        return ResponseEntity(response, HttpStatus.OK)
    }

    fun getMoviesByDirector(director: String): ResponseEntity<List<MovieDetailsResponseDTO>> {
        val response =
            movieRepository.findAllByDirector(director).stream().map { MovieDetailsResponseDTO.createFromMovie(it) }
                .toList()
        return ResponseEntity(response, HttpStatus.OK)
    }

    fun addMovieToFavorites(userId: String, movieId: String): ResponseEntity<StringResponseDTO> {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity(StringResponseDTO("User not found."), HttpStatus.BAD_REQUEST)
        } else if (!movieRepository.existsById(movieId)) {
            return ResponseEntity(StringResponseDTO("There's no such movie."), HttpStatus.BAD_REQUEST)
        }
        val user = userRepository.findById(userId)
        user.get().addFavoriteMovie(movieId)
        userRepository.save(user.get())
        return ResponseEntity(StringResponseDTO("Successfully added to favorites."), HttpStatus.OK)
    }

    fun getFavoriteMovies(userId: String): ResponseEntity<List<MovieDetailsResponseDTO>> {
        val user = userRepository.findById(userId)
        if (!userRepository.existsById(userId)) {
            return ResponseEntity(emptyList(), HttpStatus.BAD_REQUEST)
        }
        val favoriteMovies = movieRepository.findAllById(user.get().favoriteMovieIds)
        return ResponseEntity(favoriteMovies.map { MovieDetailsResponseDTO.createFromMovie(it) }, HttpStatus.OK)
    }

    fun deleteMovie(movieId: String): ResponseEntity<StringResponseDTO> {
        if (!movieRepository.existsById(movieId)) {
            return ResponseEntity(StringResponseDTO("There's no such movie."), HttpStatus.BAD_REQUEST)
        }
        movieRepository.deleteById(movieId)
        return ResponseEntity(StringResponseDTO("Movie deleted successfully."), HttpStatus.OK)
    }
}