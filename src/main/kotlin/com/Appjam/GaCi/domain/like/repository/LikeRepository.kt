package com.Appjam.GaCi.domain.like.repository

import com.Appjam.GaCi.domain.like.entity.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository : JpaRepository<Like, Long> {
    fun findByUserIdAndPostRecordId(userId: String, postRecordId: Long): Like?
    fun countByPostRecordId(postRecordId: Long): Long
}