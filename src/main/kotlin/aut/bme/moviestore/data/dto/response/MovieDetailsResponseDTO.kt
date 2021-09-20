package aut.bme.moviestore.data.dto.response

import aut.bme.moviestore.data.entity.Movie
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

class MovieDetailsResponseDTO(
    @JsonProperty("id") val id: String,
    @JsonProperty("title") val title: String,
    @JsonProperty("releaseDate") val releaseDate: LocalDate,
    @JsonProperty("director") val director: String
) {
    companion object {
        fun createFromMovie(movie: Movie) =
            MovieDetailsResponseDTO(movie.id, movie.title, movie.releaseDate, movie.director)
    }
}