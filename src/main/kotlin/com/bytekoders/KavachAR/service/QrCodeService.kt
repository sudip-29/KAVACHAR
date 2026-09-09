package com.bytekoders.KavachAR.service

import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.client.j2se.MatrixToImageWriter
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class QrCodeService {

    fun generateQrCode(content: String): ByteArray {

        val matrix = MultiFormatWriter().encode(
            content,
            BarcodeFormat.QR_CODE,
            300,
            300
        )

        val outputStream = ByteArrayOutputStream()

        MatrixToImageWriter.writeToStream(
            matrix,
            "PNG",
            outputStream
        )

        return outputStream.toByteArray()
    }
}