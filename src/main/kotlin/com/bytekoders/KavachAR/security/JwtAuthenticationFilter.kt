package com.bytekoders.KavachAR.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authHeader =
            request.getHeader("Authorization")

        // No Authorization header
        if (
            authHeader == null ||
            !authHeader.startsWith("Bearer ")
        ) {
            filterChain.doFilter(request, response)
            return
        }

        // Remove "Bearer "
        val token = authHeader.substring(7)

        try {

            // Get email from JWT
            val email =
                jwtService.extractEmail(token)

            // Check if user is already authenticated
            if (
                SecurityContextHolder
                    .getContext()
                    .authentication == null
            ) {

                // Find user from database
                val userDetails =
                    userDetailsService
                        .loadUserByUsername(email)

                // Create authentication
                val authentication =
                    UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.authorities
                    )

                authentication.details =
                    WebAuthenticationDetailsSource()
                        .buildDetails(request)

                // Set authentication
                SecurityContextHolder
                    .getContext()
                    .authentication =
                    authentication
            }

        } catch (e: Exception) {
            println("JWT Error: ${e.message}")
        }

        println("JWT Authentication: ${SecurityContextHolder.getContext().authentication}")
        filterChain.doFilter(request, response)
    }
}