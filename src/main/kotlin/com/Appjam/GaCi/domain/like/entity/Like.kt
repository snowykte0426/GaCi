package com.Appjam.GaCi.domain.like.entity

import jakarta.persistence.*

@Entity
@Table(name = "likes")
data class Like(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "post_record_id", nullable = false)
    val postRecordId: Long,

    @Column(name = "user_id", nullable = false)
    val userId: String,

    val status: Boolean
)
