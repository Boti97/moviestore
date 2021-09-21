package aut.bme.moviestore.data.repository

import aut.bme.moviestore.data.entity.Movie
import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

interface MovieRepository : CrudRepository<Movie, String> {
    fun findAllByDirectorContaining(director: String): List<Movie>
    fun findAllByReleaseDate(releaseDate: LocalDate): List<Movie>
    fun findAllByTitleContaining(title: String): List<Movie>
    fun existsByTitleContaining(title: String): Boolean
}