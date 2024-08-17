package com.Appjam.GaCi.domain.like.controller

import com.Appjam.GaCi.domain.like.service.LikeService
import com.Appjam.GaCi.domain.user.repository.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/likes")
@Tag(name = "Like API", description = "게시물 좋아요 관련 API")
class LikeController(
    private val likeService: LikeService, private val userRepository: UserRepository
) {

    @PostMapping("/{recordId}")
    @Operation(summary = "게시물 좋아요 토글", description = "해당 게시물에 대한 좋아요 상태를 토글합니다.")
    fun toggleLike(@PathVariable recordId: Long, @AuthenticationPrincipal principal: OidcUser) {
        val user =
            userRepository.findByEmail(principal.email).orElseThrow { IllegalArgumentException("User not found") }
        likeService.toggleLike(user, recordId)
    }

    @GetMapping("/{recordId}/count")
    @Operation(summary = "게시물 좋아요 개수 조회", description = "해당 게시물의 좋아요 개수를 조회합니다.")
    fun countLikes(@PathVariable recordId: Long): Long {
        return likeService.countLikes(recordId)
    }
}