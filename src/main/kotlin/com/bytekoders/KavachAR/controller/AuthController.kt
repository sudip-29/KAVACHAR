package com.bytekoders.KavachAR.controller

import com.bytekoders.KavachAR.dto.LoginRequest
import com.bytekoders.KavachAR.dto.LoginResponse
import com.bytekoders.KavachAR.dto.RegisterRequest
import com.bytekoders.KavachAR.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {

    // USER REGISTER
    @PostMapping("/register")
    fun register(
        @RequestBody request: RegisterRequest
    ): ResponseEntity<String> {

        return ResponseEntity.ok(
            authService.register(request)
        )
    }

    // ADMIN REGISTER
    @PostMapping("/register-admin")
    fun registerAdmin(
        @RequestBody request: RegisterRequest
    ): ResponseEntity<String> {

        return ResponseEntity.ok(
            authService.registerAdmin(request)
        )
    }

    // LOGIN
    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest
    ): ResponseEntity<LoginResponse> {

        return ResponseEntity.ok(
            authService.login(request)
        )
    }
}