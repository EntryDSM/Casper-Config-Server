package hs.kr.casper.configserver.application.user.port.out

import hs.kr.casper.configserver.domain.user.model.User
import java.util.*

interface FindUserPort {
    fun findByUsername(username: String): User?
    fun findById(id: UUID): User?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}