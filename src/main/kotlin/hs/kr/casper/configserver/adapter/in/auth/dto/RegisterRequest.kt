package hs.kr.casper.configserver.adapter.`in`.auth.dto

import hs.kr.casper.configserver.domain.user.model.UserRole

data class RegisterRequest(
    val username: String,
    val password: String,
    val email: String?,
    val role: UserRole = UserRole.CONFIG_READER
)