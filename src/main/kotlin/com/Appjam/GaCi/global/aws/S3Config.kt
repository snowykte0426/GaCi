package com.Appjam.GaCi.global.aws

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config(
    @Value("\${AWS_S3_ENDPOINT_URL}") private val s3EndpointUrl: String,
    @Value("\${AWS_S3_REGION}") private val s3Region: String,
    @Value("\${AWS_ACCESS_KEY_ID}") private val awsAccessKey: String,
    @Value("\${AWS_SECRET_KEY}") private val awsSecretKey: String
) {

    @Bean
    fun s3Client(): AmazonS3 {
        val awsCredentials = BasicAWSCredentials(awsAccessKey, awsSecretKey)

        return AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(s3EndpointUrl, s3Region))
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials)).build()
    }
}