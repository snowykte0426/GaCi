package com.Appjam.GaCi.domain.record.repository

import com.Appjam.GaCi.domain.record.entity.Record
import org.springframework.data.jpa.repository.JpaRepository

interface RecordRepository : JpaRepository<Record, Long>