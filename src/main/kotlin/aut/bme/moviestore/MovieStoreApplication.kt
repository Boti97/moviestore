package aut.bme.moviestore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieStoreApplication

fun main(args: Array<String>) {
    runApplication<MovieStoreApplication>(*args)
}
