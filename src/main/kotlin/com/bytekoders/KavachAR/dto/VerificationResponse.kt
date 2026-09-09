package com.bytekoders.KavachAR.dto

import com.bytekoders.KavachAR.entity.CertificateStatus
import java.time.LocalDateTime

data class VerificationResponse(
    val valid: Boolean,
    val certificateId: String?,
    val recipientName: String?,
    val certificateTitle: String?,
    val issuedAt: LocalDateTime?,
    val status: CertificateStatus?
)