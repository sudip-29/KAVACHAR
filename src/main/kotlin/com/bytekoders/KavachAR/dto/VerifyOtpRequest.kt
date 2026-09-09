package com.bytekoders.KavachAR.dto

data class VerifyOtpRequest(
    val email: String,
    val otp: String
)
