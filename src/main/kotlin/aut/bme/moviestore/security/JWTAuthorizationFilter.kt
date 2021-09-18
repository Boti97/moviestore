package aut.bme.moviestore.security

import io.jsonwebtoken.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter {
    private val HEADER = "Authorization"
    private val PREFIX = "Bearer "

    private val localLogger: Logger = LoggerFactory.getLogger(JWTAuthorizationFilter::class.java)

    @Throws(ServletException::class, IOException::class)
    protected fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        localLogger.info("Filter internal")
        try {
            if (checkJWTToken(request)) {
                val claims: Claims = validateToken(request)
                if (claims.get("authorities") != null) {
                    localLogger.info("No authorities found")
                    setUpSpringAuthentication(claims)
                } else {
                    SecurityContextHolder.clearContext()
                }
            } else {
                SecurityContextHolder.clearContext()
            }
            chain.doFilter(request, response)
        } catch (e: ExpiredJwtException) {
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
        } catch (e: UnsupportedJwtException) {
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
        } catch (e: MalformedJwtException) {
            response.status = HttpServletResponse.SC_FORBIDDEN
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.message)
        }
    }

    private fun validateToken(request: HttpServletRequest): Claims {
        val jwtToken = request.getHeader(HEADER).replace(PREFIX, "")
        val secret = "mySecretKey"
        return Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(jwtToken).getBody()
    }

    private fun setUpSpringAuthentication(claims: Claims) {
        localLogger.info("Set up spring authentication")
        val auth = UsernamePasswordAuthenticationToken(
            claims.getSubject(),
            null,
            listOf()
            //claims["authorities"].stream().map<Any> { SimpleGrantedAuthority() }.collect(Collectors.toList())
        )
        SecurityContextHolder.getContext().setAuthentication(auth)
    }

    private fun checkJWTToken(request: HttpServletRequest): Boolean {
        localLogger.info("Checking token")
        val authenticationHeader = request.getHeader(HEADER)
        return authenticationHeader != null && authenticationHeader.startsWith(PREFIX)
    }
}