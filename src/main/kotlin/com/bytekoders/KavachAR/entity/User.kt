package com.bytekoders.KavachAR.entity

import jakarta.persistence.*

@Entity
<<<<<<< HEAD
@Table(
    name = "users",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["username"]),
        UniqueConstraint(columnNames = ["email"])
    ]
)
=======
@Table(name = "users")
>>>>>>> eb5e4e62bd25e6089e63ee0db56b576dba4f7abc
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

<<<<<<< HEAD
    @Column(nullable = false, unique = true)
=======
    @Column(nullable = false)
>>>>>>> eb5e4e62bd25e6089e63ee0db56b576dba4f7abc
    var username: String = "",

    @Column(nullable = false, unique = true)
    var email: String = "",

    @Column(nullable = false)
    var password: String = "",

    @Column(nullable = false)
    var role: String = "USER"
)