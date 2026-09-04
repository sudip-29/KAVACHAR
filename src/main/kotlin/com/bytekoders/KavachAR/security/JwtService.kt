package com.bytekoders.KavachAR.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService {

    private val secretKey: SecretKey =
        Keys.hmacShaKeyFor(
            "my-secret-key-for-kavachar-authentication-2026"
                .toByteArray()
        )

    private val expirationTime =
        1000L * 60 * 60


    // Generate JWT token
    fun generateToken(
        email: String,
        role: String
    ): String {

        return Jwts.builder()
            .subject(email)
            .claim("role", role)
            .issuedAt(Date())
            .expiration(
                Date(
                    System.currentTimeMillis()
                            + expirationTime
                )
            )
            .signWith(secretKey)
            .compact()
    }


    // Extract email from JWT
    fun extractEmail(
        token: String
    ): String {

        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload
            .subject
    }
}