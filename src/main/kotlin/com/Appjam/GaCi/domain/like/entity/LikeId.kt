package com.Appjam.GaCi.domain.like.entity

import java.io.Serializable

data class LikeId(
    val record: Long = 0, val user: Long = 0
) : Serializable
