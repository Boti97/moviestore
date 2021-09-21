package aut.bme.moviestore.service

import aut.bme.moviestore.data.dto.response.ResponseDTO
import aut.bme.moviestore.data.dto.response.UserResponseDTO
import aut.bme.moviestore.data.entity.User
import aut.bme.moviestore.data.repository.UserRepository
import aut.bme.moviestore.data.util.Role
import aut.bme.moviestore.data.util.password.PasswordManager
import aut.bme.moviestore.security.JWTTokenGenerator.Companion.getJWTToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserService(private val userRepository: UserRepository) {

    fun register(name: String, email: String, password: String): ResponseEntity<ResponseDTO> {
        if (userRepository.existsByEmail(email)) {
            return ResponseEntity(
                ResponseDTO(false, "There's already a user registered with this email.", null),
                HttpStatus.BAD_REQUEST
            )
        }

        val salt = PasswordManager.generateSalt()
        val newUser =
            User(null, name, email, PasswordManager.hashAndSalt(password, salt), salt, Role.USER, mutableListOf())

        userRepository.save(newUser)
        return ResponseEntity(ResponseDTO(true, "Successful registration.", null), HttpStatus.OK)
    }

    fun login(email: String, password: String): ResponseEntity<ResponseDTO> {
        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }

        val user: Optional<User> = userRepository.findByEmail(email)
        val match: Boolean = PasswordManager.match(user.get().password, password, user.get().salt)
        if (match) {
            val userResponseDTO = UserResponseDTO.createUserDTO(userRepository.save(user.get()))
            userResponseDTO.token =
                getJWTToken(
                    userResponseDTO.name,
                    user.get().role.toString(),
                    userResponseDTO.id
                )
            return ResponseEntity(ResponseDTO(true, "Success", userResponseDTO), HttpStatus.OK)
        }
        return ResponseEntity(null, HttpStatus.BAD_REQUEST)
    }

    fun deleteUser(userId: String): ResponseEntity<ResponseDTO> {
        val reponseMessage = ResponseDTO(true, "Success", null)
        return ResponseEntity(reponseMessage, HttpStatus.OK)
    }
}