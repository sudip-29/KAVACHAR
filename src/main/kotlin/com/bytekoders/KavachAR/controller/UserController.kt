package com.bytekoders.KavachAR.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController {

    @GetMapping("/profile")
    fun profile(
        authentication: Authentication
    ): String {

        return "Welcome ${authentication.name}. You are authenticated."
    }
}