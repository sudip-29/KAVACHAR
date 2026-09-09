package com.bytekoders.KavachAR.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import java.util.Base64
import javax.crypto.SecretKey

@Service
class JwtService(

    @Value("\${jwt.secret}")
    private val jwtSecret: String,

    @Value("\${jwt.expiration}")
    private val expirationTime: Long

) {

    private val secretKey: SecretKey by lazy {
        val rawBytes: ByteArray = try {
            val base64Pattern = Regex("^[A-Za-z0-9+/=]+$")
            if (base64Pattern.matches(jwtSecret) && jwtSecret.length % 4 == 0) {
                Base64.getDecoder().decode(jwtSecret)
            } else {
                jwtSecret.toByteArray()
            }
        } catch (e: IllegalArgumentException) {
            jwtSecret.toByteArray()
        }

        val keyBytes: ByteArray = if (rawBytes.size >= 48) {
            rawBytes
        } else {
            val digest = java.security.MessageDigest.getInstance("SHA-512").digest(rawBytes)
            digest.copyOf(48)
        }

        Keys.hmacShaKeyFor(keyBytes)
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
            .signWith(secretKey, SignatureAlgorithm.HS384)
            .compact()
    }

    fun extractEmail(token: String): String {

        val payload = Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload

        return payload.subject
    }
}