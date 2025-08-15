package hs.kr.casper.configserver.infrastructure.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties
) {
    
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey.toByteArray())
    
    fun generateToken(username: String, authorities: List<String> = listOf("CONFIG_ADMIN")): String {
        val now = Instant.now()
        val expiration = now.plusMillis(jwtProperties.accessTokenExpiration)
        
        return Jwts.builder()
            .subject(username)
            .issuer(jwtProperties.issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiration))
            .claim("authorities", authorities)
            .signWith(secretKey)
            .compact()
    }
    
    fun validateToken(token: String): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
            true
        } catch (e: Exception) {
            false
        }
    }
    
    fun getUsername(token: String): String {
        return getClaims(token).subject
    }
    
    fun getAuthorities(token: String): List<String> {
        val claims = getClaims(token)
        @Suppress("UNCHECKED_CAST")
        return claims["authorities"] as? List<String> ?: emptyList()
    }
    
    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }
}