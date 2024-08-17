package com.Appjam.GaCi.domain.like.service

import com.Appjam.GaCi.domain.like.entity.Like
import com.Appjam.GaCi.domain.like.repository.LikeRepository
import com.Appjam.GaCi.domain.record.repository.RecordRepository
import com.Appjam.GaCi.domain.user.entity.User
import org.springframework.stereotype.Service

@Service
class LikeService(
    private val recordRepository: RecordRepository, private val likeRepository: LikeRepository
) {

    @jakarta.transaction.Transactional
    fun toggleLike(user: User, recordId: Long): Boolean {
        val userId = user.toString()
        val existingLike = likeRepository.findByUserIdAndPostRecordId(userId, recordId)
        return if (existingLike != null) {
            likeRepository.delete(existingLike)
            false
        } else {
            likeRepository.save(Like(userId = userId, postRecordId = recordId, status = true))
            true
        }
    }

    fun getLikesCount(recordId: Long): Long {
        return likeRepository.countByPostRecordId(recordId)
    }

    fun countLikes(recordId: Long): Long {
        return likeRepository.countByPostRecordId(recordId)
    }
}

