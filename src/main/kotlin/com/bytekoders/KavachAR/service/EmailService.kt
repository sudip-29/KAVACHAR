package com.bytekoders.KavachAR.service

interface EmailService {

    fun sendOtpEmail(
        recipientEmail: String,
        otp: String
    )
}
