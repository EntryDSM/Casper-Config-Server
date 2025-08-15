package hs.kr.casper.configserver.domain.user.model

import java.time.LocalDateTime
import java.util.*

data class User(
    val id: UUID,
    val username: String,
    val password: String,
    val email: String?,
    val role: UserRole,
    val enabled: Boolean = true,
    val createdAt: LocalDateTime,
    val lastLoginAt: LocalDateTime?
)