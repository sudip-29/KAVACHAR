package com.bytekoders.KavachAR.controller

import com.bytekoders.KavachAR.service.QrCodeService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/cert-verification")
class QrCodeController(
    private val qrCodeService: QrCodeService,
    @Value("\${app.certificate.verification-url}")
    private val verificationBaseUrl: String
) {

    @GetMapping(
        "/{certificateId}/qr",
        produces = [MediaType.IMAGE_PNG_VALUE]
    )
    fun generateQr(
        @PathVariable certificateId: String
    ): ResponseEntity<ByteArray> {

        val verificationUrl = "$verificationBaseUrl/$certificateId/verify"

        return ResponseEntity.ok(
            qrCodeService.generateQrCode(verificationUrl)
        )
    }
}