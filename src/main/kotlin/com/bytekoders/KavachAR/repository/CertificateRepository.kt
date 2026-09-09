package com.bytekoders.KavachAR.repository

import com.bytekoders.KavachAR.entity.Certificate
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CertificateRepository : JpaRepository<Certificate, Long> {

    // Used by the public certificate verification API
    fun findByCertificateId(certificateId: String): Optional<Certificate>

    fun findByUserIdAndCertificateTitle(
        userId: String,
        certificateTitle: String
    ): Optional<Certificate>
}