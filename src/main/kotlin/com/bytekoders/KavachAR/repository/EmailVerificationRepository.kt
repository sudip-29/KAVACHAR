package com.bytekoders.KavachAR.repository

import com.bytekoders.KavachAR.entity.EmailVerification
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface EmailVerificationRepository : JpaRepository<EmailVerification, Long> {

    fun findByEmail(email: String): Optional<EmailVerification>

    fun deleteByEmail(email: String)

    fun deleteByEmailAndPurpose(
        email: String,
        purpose: String
    )

    fun findTopByEmailAndPurposeAndVerifiedFalseOrderByIdDesc(
        email: String,
        purpose: String
    ): Optional<EmailVerification>
}