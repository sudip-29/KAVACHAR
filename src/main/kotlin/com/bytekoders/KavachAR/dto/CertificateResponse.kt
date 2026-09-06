package com.bytekoders.KavachAR.dto

import com.bytekoders.KavachAR.entity.CertificateStatus
import java.time.LocalDateTime

data class CertificateResponse(
    val certificateId: String,
    val userId: String,
    val recipientName: String,
    val certificateTitle: String,
    val issuedAt: LocalDateTime,
    val verificationUrl: String,
    val status: CertificateStatus
)