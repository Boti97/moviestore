package aut.bme.moviestore.data.repository

import aut.bme.moviestore.data.entity.Movie
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate
import java.util.*

interface MovieRepository : CrudRepository<Movie, String> {
    fun findAllByDirector(director: String): List<Movie>

    fun findAllByReleaseDate(releaseDate: LocalDate): List<Movie>

    fun findByTitle(title: String): Optional<Movie>
}