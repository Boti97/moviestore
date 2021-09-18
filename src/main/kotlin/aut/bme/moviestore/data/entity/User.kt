package aut.bme.moviestore.data.entity

import aut.bme.moviestore.data.dto.response.UserDetailsResponseDTO
import aut.bme.moviestore.data.dto.response.UserResponseDTO
import aut.bme.moviestore.data.util.password.PasswordSerializer
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javax.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private var id: String,

    @Column(name = "name", nullable = false)
    private var name: String,

    @Column(name = "email", nullable = false)
    private var email: String,

    @Column(name = "password", nullable = false)
    //@JsonSerialize(using = PasswordSerializer.class)
    private var password: ByteArray,

    @Column(name = "salt", nullable = false)
    //@JsonSerialize(using = PasswordSerializer.class)
    private var salt: ByteArray,

    @ElementCollection
    @Column(name = "favorite_movie_ids")
    private var favoriteMovieIds: List<String>
) {
    fun addFavoriteMovie(movieId: String) {
        favoriteMovieIds.plus(movieId)
    }

    fun removeFavoriteMove(movieId: String) {
        favoriteMovieIds.minus(movieId)
    }

    fun createUserDetailsResponseDTO() = UserDetailsResponseDTO(id, name, email, favoriteMovieIds)

    fun createUserResponseDTO() = UserResponseDTO(id, name, email)
}