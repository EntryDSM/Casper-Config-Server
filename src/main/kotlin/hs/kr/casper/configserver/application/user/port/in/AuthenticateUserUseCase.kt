package hs.kr.casper.configserver.application.user.port.`in`

import hs.kr.casper.configserver.domain.user.model.User

interface AuthenticateUserUseCase {
    fun authenticate(username: String, password: String): User?
    fun updateLastLogin(user: User)
}