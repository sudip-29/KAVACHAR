package com.bytekoders.KavachAR.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class ServiceAuthenticationFilter(
    @Value("\${django.service-token}")
    private val djangoServiceToken: String
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authHeader = request.getHeader("Authorization")

        if (
            authHeader == null ||
            !authHeader.startsWith("Bearer ")
        ) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.substring(7)

        if (token == djangoServiceToken) {

            val authentication =
                UsernamePasswordAuthenticationToken(
                    "django-admin-service",
                    null,
                    listOf(
                        SimpleGrantedAuthority("ROLE_DJANGO_SERVICE")
                    )
                )

            SecurityContextHolder
                .getContext()
                .authentication = authentication
        }

        filterChain.doFilter(request, response)
    }
}