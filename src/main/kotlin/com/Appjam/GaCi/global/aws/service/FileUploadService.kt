package com.Appjam.GaCi.global.aws.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class FileUploadService(private val s3Client: AmazonS3) {

    fun uploadFile(file: MultipartFile, bucketName: String): String {
        val fileName = "${UUID.randomUUID()}-${file.originalFilename}"
        val fileObj = ObjectMetadata()
        fileObj.contentLength = file.size
        s3Client.putObject(bucketName, fileName, file.inputStream, fileObj)
        return s3Client.getUrl(bucketName, fileName).toString()
    }
}