package hs.kr.casper.configserver.application.user.port.out

import hs.kr.casper.configserver.domain.user.model.User

interface SaveUserPort {
    fun save(user: User): User
}