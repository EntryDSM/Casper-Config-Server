package hs.kr.casper.configserver.infrastructure.error

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: HttpStatus,
    val error: String,
    val path: String
)