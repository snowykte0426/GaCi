package com.Appjam.GaCi.domain.record.controller

import com.Appjam.GaCi.domain.record.entity.Record
import com.Appjam.GaCi.domain.record.repository.RecordRepository
import com.Appjam.GaCi.domain.user.entity.User
import com.Appjam.GaCi.domain.user.repository.UserRepository
import com.Appjam.GaCi.global.aws.service.FileUploadService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@Controller
@RequestMapping("/test")
class TestController(
    private val recordRepository: RecordRepository,
    private val userRepository: UserRepository,
    private val fileUploadService: FileUploadService
) {

    @GetMapping("/form")
    fun showForm(model: Model): String {
        model.addAttribute("recordForm", RecordForm())
        return "form"
    }

    @PostMapping("/form")
    fun submitForm(
        @ModelAttribute recordForm: RecordForm,
        @RequestParam("picture") picture: MultipartFile,
        @AuthenticationPrincipal principal: OidcUser
    ): String {
        val email = principal.email
        val user: User = userRepository.findByEmail(email).orElseThrow { IllegalArgumentException("User not found") }

        val pictureUrl = fileUploadService.uploadFile(picture, "appjam-27th")
        val currentDateTime = LocalDateTime.now()
        val record = Record(
            title = recordForm.title,
            description = recordForm.description,
            picture = pictureUrl,
            writer = user,
            createdAt = currentDateTime
        )
        recordRepository.save(record)
        return "redirect:/test/form"
    }

    @GetMapping("/records")
    fun viewRecords(model: Model): String {
        val records = recordRepository.findAll()
        model.addAttribute("records", records)
        return "recordList"
    }
}

data class RecordForm(
    var title: String = "", var description: String = ""
)