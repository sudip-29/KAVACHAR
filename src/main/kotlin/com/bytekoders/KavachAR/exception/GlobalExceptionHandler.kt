package com.bytekoders.KavachAR.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(
        exception: RuntimeException
    ): ResponseEntity<String> {

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(exception.message ?: "Something went wrong")
    }
}