package com.Appjam.GaCi.domain.record.repository

import com.Appjam.GaCi.domain.record.entity.PostRecord
import org.springframework.data.jpa.repository.JpaRepository

interface RecordRepository : JpaRepository<PostRecord, Long>