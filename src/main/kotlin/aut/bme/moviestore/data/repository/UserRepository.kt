package aut.bme.moviestore.data.repository

import aut.bme.moviestore.data.entity.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository: CrudRepository<User, String> {
    fun findByEmail(email: String): Optional<User>

    fun findByName(name: String)
}