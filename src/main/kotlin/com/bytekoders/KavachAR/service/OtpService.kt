package com.bytekoders.KavachAR.service

import com.bytekoders.KavachAR.entity.EmailVerification
import com.bytekoders.KavachAR.repository.EmailVerificationRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.time.LocalDateTime

@Service
class OtpService(
    private val otpRepository: EmailVerificationRepository,
    private val passwordEncoder: PasswordEncoder,
    private val emailService: EmailService
) {

    companion object {
        private const val REGISTRATION_PURPOSE = "REGISTRATION"
        private const val OTP_EXPIRATION_MINUTES = 10L
        private const val MAX_ATTEMPTS = 5
    }

    private val secureRandom = SecureRandom()

    // =========================
    // SEND REGISTRATION OTP
    // =========================
    fun sendRegistrationOtp(email: String, username: String, password: String, role: String) {

        // Remove any previous registration OTP
        otpRepository.deleteByEmailAndPurpose(
            email,
            REGISTRATION_PURPOSE
        )

        // Generate 6-digit OTP
        val otp = String.format(
            "%06d",
            secureRandom.nextInt(1_000_000)
        )

        // Hash OTP before storing
        val otpHash = passwordEncoder.encode(otp) ?: ""

        // Hash password BEFORE storing pending registration
        val passwordHash = passwordEncoder.encode(password) ?: ""

        val verificationOtp = EmailVerification(
            email = email,
            otpHash = otpHash,
            expiresAt = LocalDateTime.now()
                .plusMinutes(OTP_EXPIRATION_MINUTES),
            purpose = REGISTRATION_PURPOSE,
            attempts = 0,
            verified = false,
            username = username,
            password = passwordHash,
            role = role
        )

        otpRepository.save(verificationOtp)

        // Send plain OTP to user's email
        emailService.sendOtpEmail(
            recipientEmail = email,
            otp = otp
        )
    }

    // =========================
    // VERIFY REGISTRATION OTP
    // =========================
    fun verifyRegistrationOtp(
        email: String,
        otp: String
    ): EmailVerification {

        val verificationOtp = otpRepository
            .findTopByEmailAndPurposeAndVerifiedFalseOrderByIdDesc(
                email,
                REGISTRATION_PURPOSE
            )
            .orElseThrow {
                RuntimeException("No active OTP found")
            }

        // Check expiration
        if (LocalDateTime.now().isAfter(verificationOtp.expiresAt)) {
            otpRepository.delete(verificationOtp)
            throw RuntimeException("OTP has expired")
        }

        // Check maximum attempts
        if (verificationOtp.attempts >= MAX_ATTEMPTS) {
            otpRepository.delete(verificationOtp)
            throw RuntimeException("Too many incorrect attempts")
        }

        // Increment attempt count
        verificationOtp.attempts++

        // Check OTP
        val validOtp = passwordEncoder.matches(
            otp,
            verificationOtp.otpHash
        )

        if (!validOtp) {
            otpRepository.save(verificationOtp)
            throw RuntimeException("Invalid OTP")
        }

        // Mark as verified
        verificationOtp.verified = true
        otpRepository.save(verificationOtp)

        return verificationOtp
    }
}
