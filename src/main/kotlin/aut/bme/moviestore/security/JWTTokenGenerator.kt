package aut.bme.moviestore.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import java.util.*
import java.util.stream.Collectors

class JWTTokenGenerator {
    private fun JWTTokenGenerator() {}

    companion object {
        fun getJWTToken(username: String?, role: String, id: String?): String? {
            val secretKey = "mySecretKey"
            val grantedAuthorities: List<GrantedAuthority> = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_$role")
            val token: String = Jwts
                .builder()
                .setId(id)
                .setSubject(username)
                .claim(
                    "authorities",
                    grantedAuthorities.stream()
                        .map<Any>(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
                )
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.ES256, secretKey.toByteArray())
                .compact()
            return "Bearer $token"
        }
    }
}