package com.bytekoders.KavachAR.controller

import com.bytekoders.KavachAR.dto.CertificateResponse
import com.bytekoders.KavachAR.dto.GenerateCertificateRequest
import com.bytekoders.KavachAR.service.CertificateService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth/certificates")
class CertificateController(
    private val certificateService: CertificateService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun generateCertificate(
        @Valid @RequestBody request: GenerateCertificateRequest
    ): CertificateResponse {
        return certificateService.generateCertificate(request)
    }
}