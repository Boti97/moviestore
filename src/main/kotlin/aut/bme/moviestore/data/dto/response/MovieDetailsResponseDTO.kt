package aut.bme.moviestore.data.dto.response

import java.time.LocalDate

class MovieDetailsResponseDTO(
    val id: String,
    val title: String,
    val releaseDate: LocalDate,
    val director: String
)