package com.Appjam.GaCi.domain.record.entity

import com.Appjam.GaCi.domain.user.entity.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Record(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,

    val title: String,

    val description: String,

    val picture: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne @JoinColumn(name = "writer_id", nullable = false) val writer: User
)