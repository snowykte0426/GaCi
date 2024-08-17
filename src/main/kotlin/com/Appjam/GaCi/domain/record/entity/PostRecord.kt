package com.Appjam.GaCi.domain.record.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "post_records")
data class PostRecord(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val title: String,

    val description: String,

    @Column(name = "picture_url")
    val picture: String,

    @Column(name = "user_email")
    val user: String,

    @Column(name = "created_at")
    val createdAt: LocalDateTime
)