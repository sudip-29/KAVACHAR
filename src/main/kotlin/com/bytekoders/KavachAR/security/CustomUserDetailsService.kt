package com.bytekoders.KavachAR.security

import com.bytekoders.KavachAR.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(
        email: String
    ): UserDetails {

        val user =
            userRepository.findByEmail(email)
                .orElseThrow {
                    RuntimeException(
                        "User not found"
                    )
                }

        return User.builder()
            .username(user.email)
            .password(user.password)
            .roles(user.role)
            .build()
    }
}