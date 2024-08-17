package com.Appjam.GaCi.domain.user.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import com.Appjam.GaCi.domain.record.entity.Record

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,

    @Column(unique = true) @NotNull val email: String,

    @NotNull @Enumerated(EnumType.STRING) val role: Role,

    var name: String? = null,
    var picture: String? = null,

    @OneToOne(mappedBy = "writer", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var record: Record? = null
) {
    fun update(name: String?, picture: String?): User {
        return this.copy(name = name, picture = picture)
    }
}