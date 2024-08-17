package com.Appjam.GaCi.global.auth.Controller

import com.Appjam.GaCi.global.auth.dto.SessionUser
import com.Appjam.GaCi.global.auth.service.PostService
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.awt.print.Pageable

@Controller
class HomeController(
    private val postService: PostService,
    private val httpSession: HttpSession
) {

    // 메인 화면 - 게시판 목록
    @GetMapping("/")
    fun postList(pageable: Pageable, model: Model): String {
        val posts = postService.findAllPosts(
            pageable = TODO()
        )
        model.addAttribute("posts", posts)

        // 세션에서 사용자 정보 꺼내기
        val user = httpSession.getAttribute("user") as? SessionUser
        user?.let {
            model.addAttribute("userName", it.name)
        }

        return "posts/list"
    }
}