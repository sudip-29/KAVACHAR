package com.bytekoders.KavachAR.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailServiceImpl(
    private val mailSender: JavaMailSender
) : EmailService {

    override fun sendOtpEmail(
        recipientEmail: String,
        otp: String
    ) {
        val message = SimpleMailMessage().apply {
            from = System.getenv("MAIL_USERNAME")
            setTo(recipientEmail)
            subject = "KavachAR - Email Verification OTP"
            text = """
                Hello,

                Your KavachAR email verification OTP is:

                $otp

                This OTP is valid for 10 minutes.

                If you did not request this verification, please ignore this email.

                Regards,
                KavachAR Team
            """.trimIndent()
        }

        mailSender.send(message)
    }
}
