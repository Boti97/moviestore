package aut.bme.moviestore.data.repository

import aut.bme.moviestore.data.entity.Movie
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface MovieRepository : CrudRepository<Movie, String> {
    fun findAllByDirector(director: String): List<Movie>
    fun findAllByReleaseDate(releaseDate: LocalDate): List<Movie>
    fun findAllByTitle(title: String): List<Movie>
    fun existsByTitle(title: String): Boolean
}