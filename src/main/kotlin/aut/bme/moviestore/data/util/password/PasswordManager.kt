package aut.bme.moviestore.data.util.password

import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.security.spec.KeySpec
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


class PasswordManager {

    companion object {
        fun match(hashedPassword: ByteArray?, clearPassword: String, salt: ByteArray?): Boolean {
            val isMatching: Boolean
            val newHashed = hashAndSalt(clearPassword, salt)
            isMatching = Arrays.equals(hashedPassword, newHashed)
            return isMatching
        }

        fun hashAndSalt(clearPassword: String, salt: ByteArray?): ByteArray {
            val spec: KeySpec = PBEKeySpec(clearPassword.toCharArray(), salt, 65536, 128)
            val factory: SecretKeyFactory
            var hash = ByteArray(0)
            factory = try {
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
                return hash
            }
            try {
                hash = factory.generateSecret(spec).encoded
            } catch (e: InvalidKeySpecException) {
                e.printStackTrace()
            }
            return hash
        }

        fun generateSalt(): ByteArray {
            val random = SecureRandom()
            val salt = ByteArray(16)
            random.nextBytes(salt)
            return salt
        }
    }
}