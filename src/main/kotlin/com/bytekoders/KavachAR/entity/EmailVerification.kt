package com.bytekoders.KavachAR.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "email_verifications")
data class EmailVerification(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var email: String = "",

    @Column(nullable = false)
    var otpHash: String = "",

    @Column(nullable = false)
    var expiresAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var purpose: String = "",

    @Column(nullable = false)
    var attempts: Int = 0,

    @Column(nullable = false)
    var verified: Boolean = false,

    @Column(nullable = false)
    var username: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    var role: String = "USER"
)