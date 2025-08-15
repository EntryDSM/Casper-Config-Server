package hs.kr.casper.configserver.adapter.`in`.auth.dto

import hs.kr.casper.configserver.domain.user.model.UserRole
import java.util.*

data class RegisterResponse(
    val id: UUID,
    val username: String,
    val email: String?,
    val role: UserRole,
    val enabled: Boolean
)