package com.bytekoders.KavachAR.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    // Encrypt passwords using BCrypt
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    // Authentication provider
    @Bean
    fun authenticationProvider(
        userDetailsService: UserDetailsService,
        passwordEncoder: PasswordEncoder
    ): DaoAuthenticationProvider {

        return DaoAuthenticationProvider(userDetailsService).apply {
            setPasswordEncoder(passwordEncoder)
        }
    }

    // Authentication manager
    @Bean
    fun authenticationManager(
        authenticationConfiguration: AuthenticationConfiguration
    ): AuthenticationManager {

        return authenticationConfiguration.authenticationManager
    }

    // Spring Security configuration
    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        authenticationProvider: DaoAuthenticationProvider
    ): SecurityFilterChain {

        http
            // Disable CSRF because we are building a REST API
            .csrf { csrf ->
                csrf.disable()
            }

            // Configure authorization
            .authorizeHttpRequests { auth ->

                // Authentication APIs are public
                auth.requestMatchers("/api/auth/**").permitAll()

                // Only ADMIN can access admin APIs
                auth.requestMatchers("/api/admin/**")
                    .hasRole("ADMIN")

                // USER and ADMIN can access user APIs
                auth.requestMatchers("/api/user/**")
                    .hasAnyRole("USER", "ADMIN")

                // All other APIs require authentication
                auth.anyRequest().authenticated()
            }

            // JWT uses stateless sessions
            .sessionManagement { session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }

            // Set authentication provider
            .authenticationProvider(authenticationProvider)

            // JWT filter runs before username/password authentication
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }
}