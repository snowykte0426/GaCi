package com.Appjam.GaCi.domain.user.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,

    @Column(unique = true) @NotNull val email: String,

    @NotNull @Enumerated(EnumType.STRING) val role: Role,

    var name: String? = null, var picture: String? = null
) {
    fun update(name: String?, picture: String?): User {
        return this.copy(name = name, picture = picture)
    }
}