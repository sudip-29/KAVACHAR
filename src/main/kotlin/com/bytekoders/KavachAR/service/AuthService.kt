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
<<<<<<< HEAD

    private val userRepository: UserRepository,

    private val passwordEncoder: PasswordEncoder,

    private val authenticationManager: AuthenticationManager,

    private val jwtService: JwtService
) {

    // ==========================================
    // USER REGISTER
    // ==========================================

    fun register(request: RegisterRequest): String {

        // Check username
        if (userRepository.existsByUsername(request.username)) {
            throw RuntimeException("Username already exists")
        }

        // Check email
=======
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {

    // =========================
    // USER REGISTER
    // =========================

    fun register(request: RegisterRequest): String {

>>>>>>> eb5e4e62bd25e6089e63ee0db56b576dba4f7abc
        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }

<<<<<<< HEAD
        // Create USER
        val user = User(
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password) ?: "",
            role = "USER"
        )

        // Save USER
=======
        val encodedPassword =
            passwordEncoder.encode(request.password) ?: ""

        val user = User(
            username = request.username,
            email = request.email,
            password = encodedPassword,
            role = "USER"
        )

>>>>>>> eb5e4e62bd25e6089e63ee0db56b576dba4f7abc
        userRepository.save(user)

        return "User registered successfully"
    }


<<<<<<< HEAD
    // ==========================================
    // ADMIN REGISTER
    // ==========================================

    fun registerAdmin(request: RegisterRequest): String {

        // Check username
        if (userRepository.existsByUsername(request.username)) {
            throw RuntimeException("Username already exists")
        }

        // Check email
=======
    // =========================
    // ADMIN REGISTER
    // =========================

    fun registerAdmin(request: RegisterRequest): String {

>>>>>>> eb5e4e62bd25e6089e63ee0db56b576dba4f7abc
        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }

<<<<<<< HEAD
        // Create ADMIN
        val admin = User(
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password) ?: "",
            role = "ADMIN"
        )

        // Save ADMIN
=======
        val encodedPassword =
            passwordEncoder.encode(request.password) ?: ""

        val admin = User(
            username = request.username,
            email = request.email,
            password = encodedPassword,
            role = "ADMIN"
        )

>>>>>>> eb5e4e62bd25e6089e63ee0db56b576dba4f7abc
        userRepository.save(admin)

        return "Admin registered successfully"
    }


<<<<<<< HEAD
    // ==========================================
    // LOGIN
    // ==========================================

    fun login(request: LoginRequest): LoginResponse {

        // Authenticate using username OR email
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.emailOrUsername,
                request.password
            )
        )

        // Find user using username OR email
=======
    // =========================
    // LOGIN
    // =========================

    fun login(request: LoginRequest): LoginResponse {

>>>>>>> eb5e4e62bd25e6089e63ee0db56b576dba4f7abc
        val user = userRepository
            .findByEmailOrUsername(
                request.emailOrUsername,
                request.emailOrUsername
            )
            .orElseThrow {
                RuntimeException("User not found")
            }

<<<<<<< HEAD
        // Generate JWT
=======
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                user.email,
                request.password
            )
        )

>>>>>>> eb5e4e62bd25e6089e63ee0db56b576dba4f7abc
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