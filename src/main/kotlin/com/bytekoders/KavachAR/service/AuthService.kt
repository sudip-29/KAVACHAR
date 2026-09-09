package com.bytekoders.KavachAR.service

import com.bytekoders.KavachAR.dto.LoginRequest
import com.bytekoders.KavachAR.dto.LoginResponse
import com.bytekoders.KavachAR.dto.RegisterRequest
import com.bytekoders.KavachAR.entity.User
import com.bytekoders.KavachAR.repository.UserRepository
import com.bytekoders.KavachAR.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val otpService: OtpService
) {

    // =========================
    // USER REGISTER
    // =========================
    fun register(request: RegisterRequest): String {

        if (userRepository.existsByUsername(request.username)) {
            throw RuntimeException("Username already exists")
        }

        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }

        otpService.sendRegistrationOtp(
            email = request.email,
            username = request.username,
            password = request.password,
            role = "USER"
        )

        return "OTP sent to your email"
    }

    // =========================
    // ADMIN REGISTER
    // =========================
    fun registerAdmin(request: RegisterRequest): String {

        if (userRepository.existsByUsername(request.username)) {
            throw RuntimeException("Username already exists")
        }

        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists")
        }

        otpService.sendRegistrationOtp(
            email = request.email,
            username = request.username,
            password = request.password,
            role = "ADMIN"
        )

        return "OTP sent to your email"
    }

    // =========================
    // VERIFY REGISTRATION OTP
    // =========================
    fun verifyRegistrationOtp(
        email: String,
        otp: String
    ): String {

        val verificationOtp = otpService.verifyRegistrationOtp(
            email = email,
            otp = otp
        )

        // Double-check that this OTP belongs to registration
        if (verificationOtp.purpose != "REGISTRATION") {
            throw RuntimeException("Invalid OTP purpose")
        }

        // Prevent creating duplicate username
        if (userRepository.existsByUsername(verificationOtp.username)) {
            throw RuntimeException("Username already exists")
        }

        // Prevent creating duplicate email
        if (userRepository.existsByEmail(verificationOtp.email)) {
            throw RuntimeException("Email already exists")
        }

        val user = User(
            username = verificationOtp.username,
            email = verificationOtp.email,
            password = verificationOtp.password
                ?: throw RuntimeException("Invalid registration state: password missing"),
            role = verificationOtp.role
        )

        userRepository.save(user)

        return if (verificationOtp.role == "ADMIN") {
            "Admin registered successfully"
        } else {
            "User registered successfully"
        }
    }

    // =========================
    // LOGIN
    // =========================
    fun login(request: LoginRequest): LoginResponse {

        // Authenticate using username OR email
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.emailOrUsername,
                request.password
            )
        )

        // Find user using username OR email
        val user = userRepository
            .findByEmailOrUsername(
                request.emailOrUsername,
                request.emailOrUsername
            )
            .orElseThrow {
                RuntimeException("User not found")
            }

        // Generate JWT
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
