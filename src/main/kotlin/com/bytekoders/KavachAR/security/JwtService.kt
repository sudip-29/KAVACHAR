package com.bytekoders.KavachAR.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService(

    @Value("\${jwt.secret}")
    private val jwtSecret: String,

    @Value("\${jwt.expiration}")
    private val expirationTime: Long

) {

    private val secretKey: SecretKey by lazy {
        Keys.hmacShaKeyFor(
            jwtSecret.toByteArray()
        )
    }

    fun generateToken(
        email: String,
        role: String
    ): String {

        return Jwts.builder()
            .subject(email)
            .claim("role", role)
            .issuedAt(Date())
            .expiration(
                Date(System.currentTimeMillis() + expirationTime)
            )
            .signWith(secretKey)
            .compact()
    }

    fun extractEmail(token: String): String {

        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
    }
}