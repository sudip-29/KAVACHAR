package com.bytekoders.KavachAR.service

import com.bytekoders.KavachAR.dto.LoginRequest
import com.bytekoders.KavachAR.dto.LoginResponse
import com.bytekoders.KavachAR.dto.RegisterRequest
import com.bytekoders.KavachAR.entity.User
import com.bytekoders.KavachAR.repository.UserRepository
import com.bytekoders.KavachAR.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {

    // =========================
    // USER REGISTER
    // =========================

    fun register(request: RegisterRequest): String {

        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }

        val encodedPassword =
            passwordEncoder.encode(request.password) ?: ""

        val user = User(
            username = request.username,
            email = request.email,
            password = encodedPassword,
            role = "USER"
        )

        userRepository.save(user)

        return "User registered successfully"
    }


    // =========================
    // ADMIN REGISTER
    // =========================

    fun registerAdmin(request: RegisterRequest): String {

        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }

        val encodedPassword =
            passwordEncoder.encode(request.password) ?: ""

        val admin = User(
            username = request.username,
            email = request.email,
            password = encodedPassword,
            role = "ADMIN"
        )

        userRepository.save(admin)

        return "Admin registered successfully"
    }


    // =========================
    // LOGIN
    // =========================

    fun login(request: LoginRequest): LoginResponse {

        val user = userRepository
            .findByEmailOrUsername(
                request.emailOrUsername,
                request.emailOrUsername
            )
            .orElseThrow {
                RuntimeException("User not found")
            }

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                user.email,
                request.password
            )
        )

        val token = jwtService.generateToken(
            user.email,
            user.role
        )

        return LoginResponse(
            token = token,
            username = user.username,
            role = user.role
        )
    }
}