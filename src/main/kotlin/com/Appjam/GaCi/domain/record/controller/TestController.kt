package com.Appjam.GaCi.domain.record.controller

import com.Appjam.GaCi.domain.like.entity.Like
import com.Appjam.GaCi.domain.like.repository.LikeRepository
import com.Appjam.GaCi.domain.record.entity.PostRecord
import com.Appjam.GaCi.domain.record.repository.RecordRepository
import com.Appjam.GaCi.domain.user.entity.User
import com.Appjam.GaCi.domain.user.repository.UserRepository
import com.Appjam.GaCi.global.aws.service.FileUploadService
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
    private val fileUploadService: FileUploadService,
    private val likeRepository: LikeRepository
) {

    @GetMapping("/form")
    fun showForm(model: Model): String {
        model.addAttribute("recordForm", RecordForm())
        return "form"
    }

    @PostMapping("/form")
    fun submitForm(
        @ModelAttribute recordForm: RecordForm, @RequestParam("picture") picture: MultipartFile
    ): String {
        val user: User = userRepository.findByEmail(recordForm.writerEmail)
            .orElseThrow { IllegalArgumentException("User not found") }

        val pictureUrl = fileUploadService.uploadFile(picture, "appjam-27th")
        val currentDateTime = LocalDateTime.now()
        val record = PostRecord(
            title = recordForm.title,
            description = recordForm.description,
            picture = pictureUrl,
            user = recordForm.writerEmail,
            createdAt = currentDateTime
        )
        val savedRecord = recordRepository.save(record)

        val like = Like(
            postRecordId = savedRecord.id,
            userId = user.email,
            status = true
        )
        likeRepository.save(like)

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
    var title: String = "", var description: String = "", var writerEmail: String = ""
)