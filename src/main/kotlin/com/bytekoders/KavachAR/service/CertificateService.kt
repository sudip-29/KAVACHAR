package com.bytekoders.KavachAR.service

import com.bytekoders.KavachAR.dto.CertificateResponse
import com.bytekoders.KavachAR.dto.GenerateCertificateRequest
import com.bytekoders.KavachAR.entity.Certificate
import com.bytekoders.KavachAR.repository.CertificateRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class CertificateService(
    private val certificateRepository: CertificateRepository,

    @Value("\${app.certificate.verification-url}")
    private val verificationBaseUrl: String
) {

    fun generateCertificate(request: GenerateCertificateRequest): CertificateResponse {

        val existingCertificate = certificateRepository
            .findByUserIdAndCertificateTitle(
                request.userId,
                request.certificateTitle
            )

        if (existingCertificate.isPresent) {
            return existingCertificate.get().toResponse()
        }

        val certificateId = generateCertificateId()

        val certificate = Certificate(
            certificateId = certificateId,
            userId = request.userId,
            recipientName = request.recipientName,
            certificateTitle = request.certificateTitle,
            issuedAt = LocalDateTime.now(),
            verificationUrl = "$verificationBaseUrl/$certificateId/verify"
        )

        return certificateRepository.save(certificate).toResponse()
    }

    private fun generateCertificateId(): String {
        return "KVR-${UUID.randomUUID().toString().replace("-", "").uppercase()}"
    }

    private fun Certificate.toResponse(): CertificateResponse {
        return CertificateResponse(
            certificateId = certificateId,
            userId = userId,
            recipientName = recipientName,
            certificateTitle = certificateTitle,
            issuedAt = issuedAt,
            verificationUrl = verificationUrl,
            status = status
        )
    }
}