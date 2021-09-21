package aut.bme.moviestore.service

import aut.bme.moviestore.data.dto.response.MovieDetailsResponseDTO
import aut.bme.moviestore.data.dto.response.ResponseDTO
import aut.bme.moviestore.data.entity.Movie
import aut.bme.moviestore.data.repository.MovieRepository
import aut.bme.moviestore.data.repository.UserRepository
import aut.bme.moviestore.data.util.LocalDateParser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.time.LocalDate


@Component
class MovieService(private val movieRepository: MovieRepository, private val userRepository: UserRepository) {

    fun getMoviesByTitle(title: String): ResponseEntity<ResponseDTO> {
        val movieResponseDTOs =
            movieRepository.findAllByTitleContaining(title).stream().map { MovieDetailsResponseDTO.createFromMovie(it) }.toList()
        val responseDTO = ResponseDTO(true, "Success.", movieResponseDTOs)
        return ResponseEntity(responseDTO, HttpStatus.OK)
    }

    fun getMoviesByReleaseDate(releaseDate: String?): ResponseEntity<ResponseDTO> {
        val parsedReleasedDate: LocalDate?
        if (releaseDate != null) {
            parsedReleasedDate = LocalDateParser.parse(releaseDate)
            if (parsedReleasedDate.equals(LocalDate.MIN)) {
                return ResponseEntity(
                    ResponseDTO(
                        false,
                        "Release date format was not correct. Requered format: yyyy.MM.dd",
                        null
                    ), HttpStatus.BAD_REQUEST
                )
            }
            val movieResponseDTOs =
                movieRepository.findAllByReleaseDate(parsedReleasedDate).stream()
                    .map { MovieDetailsResponseDTO.createFromMovie(it) }.toList()
            val responseDTO = ResponseDTO(true, "Success.", movieResponseDTOs)
            return ResponseEntity(responseDTO, HttpStatus.OK)
        }

        return ResponseEntity(ResponseDTO(false, "Request date cannot be null.", null), HttpStatus.BAD_REQUEST)
    }

    fun getMoviesByDirector(director: String): ResponseEntity<ResponseDTO> {
        val movieResponseDTOs =
            movieRepository.findAllByDirectorContaining(director).stream().map { MovieDetailsResponseDTO.createFromMovie(it) }
                .toList()
        val responseDTO = ResponseDTO(true, "Success.", movieResponseDTOs)
        return ResponseEntity(responseDTO, HttpStatus.OK)
    }

    fun addMovieToFavorites(userId: String, movieId: String): ResponseEntity<ResponseDTO> {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity(ResponseDTO(false, "User not found.", null), HttpStatus.BAD_REQUEST)
        } else if (!movieRepository.existsById(movieId)) {
            return ResponseEntity(ResponseDTO(false, "There's no such movie.", null), HttpStatus.BAD_REQUEST)
        }
        val user = userRepository.findById(userId)
        user.get().addFavoriteMovie(movieId)
        userRepository.save(user.get())
        return ResponseEntity(ResponseDTO(false, "Successfully added to favorites.", null), HttpStatus.OK)
    }

    fun getFavoriteMovies(userId: String): ResponseEntity<ResponseDTO> {
        val user = userRepository.findById(userId)
        if (!userRepository.existsById(userId)) {
            return ResponseEntity(ResponseDTO(false, "User does not exist.", null), HttpStatus.BAD_REQUEST)
        }
        val movieResponseDTOs = movieRepository.findAllById(user.get().favoriteMovieIds)
            .map { MovieDetailsResponseDTO.createFromMovie(it) }
        val responseDTO = ResponseDTO(true, "Success!", movieResponseDTOs)
        return ResponseEntity(responseDTO, HttpStatus.OK)
    }

    fun deleteMovie(movieId: String): ResponseEntity<ResponseDTO> {
        if (!movieRepository.existsById(movieId)) {
            return ResponseEntity(ResponseDTO(false, "There's no such movie.", null), HttpStatus.BAD_REQUEST)
        }
        movieRepository.deleteById(movieId)
        return ResponseEntity(ResponseDTO(false, "Movie deleted successfully.", null), HttpStatus.OK)
    }

    fun addMovie(title: String, director: String?, releaseDate: String?): ResponseEntity<ResponseDTO> {
        if (movieRepository.existsByTitleContaining(title)) {
            return ResponseEntity(ResponseDTO(false, "Movie with title already exist.", null), HttpStatus.BAD_REQUEST)
        }
        val parsedReleasedDate: LocalDate?
        if (releaseDate != null) {
            parsedReleasedDate = LocalDateParser.parse(releaseDate)
            if (parsedReleasedDate.equals(LocalDate.MIN)) {
                return ResponseEntity(
                    ResponseDTO(
                        false,
                        "Release date format was not correct. Requered format: yyyy.MM.dd",
                        null
                    ), HttpStatus.BAD_REQUEST
                )
            }
        } else {
            parsedReleasedDate = null
        }
        val newMovie = Movie(null, title, parsedReleasedDate, director)
        movieRepository.save(newMovie)
        return ResponseEntity(
            ResponseDTO(true, "Success.", MovieDetailsResponseDTO.createFromMovie(newMovie)),
            HttpStatus.OK
        )
    }

    fun removeMovieFromFavorites(userId: String, movieId: String): ResponseEntity<ResponseDTO> {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity(ResponseDTO(false, "User not found.", null), HttpStatus.BAD_REQUEST)
        } else if (!movieRepository.existsById(movieId)) {
            return ResponseEntity(ResponseDTO(false, "There's no such movie.", null), HttpStatus.BAD_REQUEST)
        }
        val user = userRepository.findById(userId)
        user.get().removeFavoriteMove(movieId)
        userRepository.save(user.get())
        return ResponseEntity(ResponseDTO(false, "Successfully removed from favorites.", null), HttpStatus.OK)
    }
}