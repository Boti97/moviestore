package aut.bme.moviestore.data.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

class UserDetailsResponseDTO(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("favoriteMovieIds") val favoriteMovieIds: List<String>
)