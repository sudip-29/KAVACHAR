package com.bytekoders.KavachAR.controller

import com.bytekoders.KavachAR.dto.CertificateResponse
import com.bytekoders.KavachAR.dto.VerificationResponse
import com.bytekoders.KavachAR.repository.CertificateRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/cert-verification")
class CertVerificationController(
    private val certificateRepository: CertificateRepository
) {

    @GetMapping("/certificates")
    fun getCertificates(): ResponseEntity<List<CertificateResponse>> {

        val certificates = certificateRepository.findAll()

        val response = certificates.map { certificate ->
            CertificateResponse(
                certificateId = certificate.certificateId,
                userId = certificate.userId,
                recipientName = certificate.recipientName,
                certificateTitle = certificate.certificateTitle,
                issuedAt = certificate.issuedAt,
                verificationUrl = certificate.verificationUrl,
                status = certificate.status
            )
        }

        return ResponseEntity.ok(response)
    }

    @GetMapping("/{certificateId}/verify")
    fun verify(
        @PathVariable certificateId: String
    ): ResponseEntity<VerificationResponse> {

        val cert = certificateRepository.findByCertificateId(certificateId)
        return if (cert.isPresent) {
            val c = cert.get()
            ResponseEntity.ok(
                VerificationResponse(
                    valid = true,
                    certificateId = c.certificateId,
                    recipientName = c.recipientName,
                    certificateTitle = c.certificateTitle,
                    issuedAt = c.issuedAt,
                    status = c.status
                )
            )
        } else {
            ResponseEntity.ok(
                VerificationResponse(
                    valid = false,
                    certificateId = null,
                    recipientName = null,
                    certificateTitle = null,
                    issuedAt = null,
                    status = null
                )
            )
        }
    }
}