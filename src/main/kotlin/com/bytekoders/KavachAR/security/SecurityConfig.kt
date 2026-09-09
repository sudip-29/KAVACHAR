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
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val serviceAuthenticationFilter: ServiceAuthenticationFilter
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

            // JWT filter runs before username/password authentication
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