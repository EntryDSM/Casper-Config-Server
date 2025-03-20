package hs.kr.casper.configserver.adapter.`in`.env.dto.response

import hs.kr.casper.configserver.domain.env.model.enum.EnvironmentOperationType
import java.time.LocalDateTime

data class EnvironmentOperationResponse(
    val success: Boolean,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val operation: EnvironmentOperationType,
)
