package com.Appjam.GaCi.domain.user.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long ?= null

    @Column(unique = true)
    @NotNull
    var email: String? ?= null
}