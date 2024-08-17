package com.Appjam.GaCi.global.auth.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostService {
    fun findAllPosts(pageable: Pageable): Page<Post> {
        // 임시 게시물 리스트
        val posts = listOf(
            Post(id = 1, title = "First Post", content = "This is the first post."),
            Post(id = 2, title = "Second Post", content = "This is the second post.")
        )
        return PageImpl(posts, pageable, posts.size.toLong())
    }
}

data class Post(
    val id: Long,
    val title: String,
    val content: String
)
