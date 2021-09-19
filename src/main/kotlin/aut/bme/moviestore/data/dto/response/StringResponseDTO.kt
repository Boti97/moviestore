package aut.bme.moviestore.data.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class StringResponseDTO(@JsonProperty("response") val response : String)