package com.Appjam.GaCi.domain.record.controller

import com.Appjam.GaCi.domain.like.service.LikeService
import com.Appjam.GaCi.domain.record.entity.PostRecord
import com.Appjam.GaCi.domain.record.repository.RecordRepository
import com.Appjam.GaCi.domain.record.response.RecordResponse
import com.Appjam.GaCi.domain.user.repository.UserRepository
import com.Appjam.GaCi.global.aws.service.FileUploadService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDateTime

@RestController
@RequestMapping("/records")
@Tag(name = "PostRecord API", description = "PostRecord 관련 API")
class RecordController(
    private val recordRepository: RecordRepository,
    private val userRepository: UserRepository,
    private val fileUploadService: FileUploadService,
    private val likeService: LikeService
) {

    @PostMapping
    @Operation(summary = "Create PostRecord", description = "새로운 레코드를 생성하고 이미지를 R2에 업로드합니다.")
    fun createRecord(
        @RequestParam("title") title: String,
        @RequestParam("description") description: String,
        @RequestParam("picture") picture: MultipartFile,
        @RequestParam("writerEmail") writerEmail: String
    ): ResponseEntity<PostRecord> {
        val writerOptional = userRepository.findByEmail(writerEmail)
        return if (writerOptional.isPresent) {
            val writer = writerOptional.get()
            val pictureUrl = fileUploadService.uploadFile(picture, "appjam-27th")
            val record = PostRecord(
                title = title, description = description, picture = pictureUrl, user = writer.toString(), createdAt = LocalDateTime.now()
            )
            ResponseEntity.ok(recordRepository.save(record))
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    @GetMapping
    @Operation(summary = "Get All Records", description = "모든 레코드를 가져옵니다.")
    fun getAllRecords(): ResponseEntity<List<RecordResponse>> {
        val records = recordRepository.findAll()
        val recordResponses = records.map { record ->
            RecordResponse(
                id = record.id,
                title = record.title,
                description = record.description,
                pictureUrl = record.picture,
                writerName = record.user
            )
        }
        return ResponseEntity.ok(recordResponses)
    }

    @GetMapping("/{recordId}/count")
    fun countLikes(@PathVariable recordId: Long): Long {
        return likeService.countLikes(recordId)
    }
}