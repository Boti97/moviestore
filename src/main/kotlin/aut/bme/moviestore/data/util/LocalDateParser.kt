package aut.bme.moviestore.data.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


class LocalDateParser {
    companion object {
        var formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

        fun parse(date: String): LocalDate {
            val parsedDate: LocalDate?
            try {
                parsedDate = LocalDate.parse(date, formatter)
            } catch (e: DateTimeParseException) {
                return LocalDate.MIN
            }
            return parsedDate
        }
    }
}