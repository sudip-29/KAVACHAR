package com.bytekoders.KavachAR.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(
    name = "certificates",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_user_id_certificate_title",
            columnNames = ["userId", "certificateTitle"]
        )
    ],
    indexes = [
        Index(name = "idx_certificate_id", columnList = "certificateId")
    ]
)
class Certificate(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    // Public identifier used for certificate verification.
    @Column(nullable = false, unique = true, updatable = false)
    val certificateId: String,

    val userId: String,

    val recipientName: String,

    val certificateTitle: String,

    val issuedAt: LocalDateTime,

    // Encoded into the certificate QR code.
    val verificationUrl: String,

    @Enumerated(EnumType.STRING)
    val status: CertificateStatus = CertificateStatus.VALID
)