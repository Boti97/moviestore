package aut.bme.moviestore.data.entity

import aut.bme.moviestore.data.util.Role
import javax.persistence.*

@Entity
@Table(name = "user")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    var id: String?,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: ByteArray,

    @Column(name = "salt", nullable = false)
    var salt: ByteArray,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: Role,

    @ElementCollection
    @Column(name = "favorite_movie_ids")
    var favoriteMovieIds: MutableList<String>
) {

    fun addFavoriteMovie(movieId: String) {
        favoriteMovieIds.add(movieId)
    }

    fun removeFavoriteMove(movieId: String) {
        favoriteMovieIds.remove(movieId)
    }
}