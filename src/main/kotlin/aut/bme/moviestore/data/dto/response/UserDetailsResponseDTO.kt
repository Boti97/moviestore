package aut.bme.moviestore.data.dto.response

class UserDetailsResponseDTO(
    val id: String,
    val name: String,
    val email: String,
    val favoriteMovieIds: List<String>
)