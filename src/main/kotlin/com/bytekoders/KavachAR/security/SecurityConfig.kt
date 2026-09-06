//package com.bytekoders.KavachAR.security
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
//
//@Configuration
//class SecurityConfig(
//    private val jwtAuthenticationFilter: JwtAuthenticationFilter
//) {
//
//    @Bean
//    fun passwordEncoder(): PasswordEncoder {
//        return BCryptPasswordEncoder()
//    }
//
//    @Bean
//    fun authenticationManager(
//        configuration: AuthenticationConfiguration
//    ): AuthenticationManager {
//        return configuration.authenticationManager
//    }
//
//    @Bean
//    fun securityFilterChain(
//        http: HttpSecurity
//    ): SecurityFilterChain {
//
//        http
//            // Disable CSRF because we are using JWT
//            .csrf { csrf ->
//                csrf.disable()
//            }
//
//            // Do not create HTTP sessions
//            .sessionManagement { session ->
//                session.sessionCreationPolicy(
//                    SessionCreationPolicy.STATELESS
//                )
//            }
//
//            .authorizeHttpRequests { auth ->
//
//
//                auth
//
//                    // Certificate APIs require authentication
//                    .requestMatchers("/api/auth/certificates/**")
//                    .authenticated()
//
//                    // REGISTER + LOGIN + REGISTER ADMIN
//                    // These URLs do NOT need JWT
//                    .requestMatchers("/api/auth/**")
//                    .permitAll()
//
//                    // ADMIN APIs require ADMIN role
//                    .requestMatchers("/api/admin/**")
//                    .hasRole("ADMIN")
//
//                    // USER APIs require login
//                    .requestMatchers("/api/user/**")
//                    .authenticated()
//
//                    // Everything else requires authentication
//                    .anyRequest()
//                    .authenticated()
//            }
//
//            // Explicit authentication/authorization responses
//            .exceptionHandling { exceptions ->
//                exceptions
//                    .authenticationEntryPoint { _, response, _ ->
//                        response.status = 401
//                    }
//                    .accessDeniedHandler { _, response, _ ->
//                        response.status = 403
//                    }
//            }
//
//            // JWT filter
//            .addFilterBefore(
//                jwtAuthenticationFilter,
//                UsernamePasswordAuthenticationFilter::class.java
//            )
//
//        return http.build()
//    }
//}







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
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
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
                    .requestMatchers(
                        "/api/auth/login",
                        "/api/auth/register",
                        "/api/auth/register-admin"
                    )
                    .permitAll()

                    // Public certificate verification endpoint (QR verification)
                    .requestMatchers("/api/auth/cert-verification/**")
                    .permitAll()

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
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            )

        return http.build()
    }
}