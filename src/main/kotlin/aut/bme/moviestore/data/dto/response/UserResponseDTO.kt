package aut.bme.moviestore.data.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponseDTO(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("email") val email: String
)