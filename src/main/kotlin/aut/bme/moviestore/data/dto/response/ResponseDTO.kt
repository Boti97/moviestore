package aut.bme.moviestore.data.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseDTO(
    @JsonProperty("success") val success: Boolean,
    @JsonProperty("message") val message: String,
    @JsonProperty("response") val response: Any?
)