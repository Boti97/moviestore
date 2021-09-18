package aut.bme.moviestore.data.util.password

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

class PasswordSerializer(t: Class<ByteArray>?) : StdSerializer<ByteArray>(t) {
    override fun serialize(value: ByteArray?, gen: JsonGenerator?, provider: SerializerProvider?) {
        gen?.writeObject(null)
    }
}