package com.Appjam.GaCi.domain.record.controller

import com.Appjam.GaCi.domain.record.entity.Record
import com.Appjam.GaCi.domain.record.repository.RecordRepository
import com.Appjam.GaCi.global.aws.service.FileUploadService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/records")
@Tag(name = "Record API", description = "Record 관련 API")
class RecordController(
    private val recordRepository: RecordRepository,
    private val fileUploadService: FileUploadService
) {

    @PostMapping
    @Operation(summary = "Create Record", description = "새로운 레코드를 생성하고 이미지를 R2에 업로드합니다.")
    fun createRecord(
        @RequestParam("title") title: String,
        @RequestParam("description") description: String,
        @RequestParam("picture") picture: MultipartFile
    ): ResponseEntity<Record> {
        val pictureUrl = fileUploadService.uploadFile(picture, "appjam-27th")
        val record = Record(
            title = title,
            description = description,
            picture = pictureUrl
        )
        return ResponseEntity.ok(recordRepository.save(record))
    }
}