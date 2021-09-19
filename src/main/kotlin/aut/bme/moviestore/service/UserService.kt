package aut.bme.moviestore.service

import aut.bme.moviestore.data.dto.response.StringResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class UserService {

    fun register(name: String, email: String, password: String): ResponseEntity<StringResponseDTO> {
        val reponseMessage = StringResponseDTO("Success")
        return ResponseEntity(reponseMessage, HttpStatus.OK)
    }

    fun login(email: String, password: String): ResponseEntity<StringResponseDTO>{
        val reponseMessage = StringResponseDTO("Success")
        return ResponseEntity(reponseMessage, HttpStatus.OK)
    }
}