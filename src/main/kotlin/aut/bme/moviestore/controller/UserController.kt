package aut.bme.moviestore.controller

import aut.bme.moviestore.data.dto.response.StringResponseDTO
import aut.bme.moviestore.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "email") email: String,
        @RequestParam(value = "password") password: String
    ): ResponseEntity<StringResponseDTO> {
        return userService.register(name, email, password)
    }

    @GetMapping("/login")
    fun login(
        @RequestParam(value = "email") email: String,
        @RequestParam(value = "password") password: String
    ): ResponseEntity<StringResponseDTO> {
        return userService.login(email, password)
    }
}