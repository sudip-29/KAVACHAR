package com.bytekoders.KavachAR.repository

import com.bytekoders.KavachAR.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>

    fun findByUsername(username: String): Optional<User>

    fun findByEmailOrUsername(
        email: String,
        username: String
    ): Optional<User>

    fun existsByEmail(email: String): Boolean
<<<<<<< HEAD

    fun existsByUsername(username: String): Boolean
=======
>>>>>>> eb5e4e62bd25e6089e63ee0db56b576dba4f7abc
}