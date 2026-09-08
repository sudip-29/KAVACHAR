package com.bytekoders.KavachAR.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val serviceAuthenticationFilter: ServiceAuthenticationFilter
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(
        configuration: AuthenticationConfiguration
    ): AuthenticationManager {
        return configuration.authenticationManager
    }

    @Bean
    fun securityFilterChain(
        http: HttpSecurity
    ): SecurityFilterChain {

        http
            .csrf { it.disable() }

            .sessionManagement {
                it.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }

            .authorizeHttpRequests { auth ->
                auth
                    //Swagger UI and API docs should be accessible without authentication
                    .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                    ).permitAll()

                    // Authentication endpoints should be accessible without authentication
                    .requestMatchers(
                        "/api/auth/login",
                        "/api/auth/register",
                        "/api/auth/register-admin"
                    )
                    .permitAll()

                    // Certificate list — Django service only
                    .requestMatchers("/api/auth/cert-verification/certificates")
                    .hasRole("DJANGO_SERVICE")

                    // Certificate verification endpoint (QR verification) requires authentication
                    .requestMatchers("/api/auth/cert-verification/**")
                    .authenticated()

                    .requestMatchers("/api/auth/certificates/**")
                    .hasRole("USER")

                    .requestMatchers("/api/admin/**")
                    .hasRole("ADMIN")

                    .requestMatchers("/api/user/**")
                    .authenticated()

                    .anyRequest()
                    .authenticated()
            }

            .exceptionHandling { exceptions ->
                exceptions
                    .authenticationEntryPoint { _, response, _ ->
                        response.sendError(401, "Unauthorized")
                    }
                    .accessDeniedHandler { _, response, _ ->
                        response.sendError(403, "Forbidden")
                    }
            }

            .addFilterBefore(
                serviceAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }
}