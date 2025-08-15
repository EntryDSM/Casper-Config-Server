package hs.kr.casper.configserver.application.user.port.`in`

import hs.kr.casper.configserver.domain.user.model.User
import hs.kr.casper.configserver.domain.user.model.UserRole

interface RegisterUserUseCase {
    fun registerUser(
        username: String,
        password: String,
        email: String?,
        role: UserRole
    ): User
}