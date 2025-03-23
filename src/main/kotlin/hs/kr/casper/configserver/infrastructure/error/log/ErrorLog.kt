package hs.kr.casper.configserver.infrastructure.error.log

import java.time.LocalDateTime

data class ErrorLog(
    val status: Int? = null,
    val message: String? = null,
    val path: String? = null,
    val timestamp: LocalDateTime = LocalDateTime.now()
) {
}