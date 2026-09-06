package com.bytekoders.KavachAR.dto

import jakarta.validation.constraints.NotBlank

data class GenerateCertificateRequest(

    @field:NotBlank
    val userId: String,

    @field:NotBlank
    val recipientName: String,

    @field:NotBlank
    val certificateTitle: String
)