package aut.bme.moviestore.data.dto.response

import aut.bme.moviestore.data.entity.User
import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponseDTO(
    @JsonProperty("id") val id: String,
    @JsonProperty("name") val name: String,
    @JsonProperty("email") val email: String,
    @JsonProperty("token") var token: String?,
) {
    companion object {
        fun createUserDTO(user: User) = UserResponseDTO(user.id!!, user.name, user.email, null)
    }
}