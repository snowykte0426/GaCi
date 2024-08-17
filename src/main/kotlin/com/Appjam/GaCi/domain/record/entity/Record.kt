package com.Appjam.GaCi.domain.record.entity

import com.Appjam.GaCi.domain.user.entity.User
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Entity
@Table(name = "record")
data class Record(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,

    @NotNull @OneToOne @JoinColumn(name = "user_email", referencedColumnName = "email") var writer: User? = null,

    @Column(unique = true) @NotNull val title: String,

    @NotNull @Column(name = "description") val description: String,

    @Column(name = "created_at") val createdAt: LocalDateTime = LocalDateTime.now(),

    var picture: String? = null
)