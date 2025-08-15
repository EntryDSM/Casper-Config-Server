package hs.kr.casper.configserver.adapter.out.persistence.user.repository

import hs.kr.casper.configserver.infrastructure.persistence.user.UserJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserJpaEntity, UUID> {
    fun findByUsername(username: String): UserJpaEntity?
    fun existsByUsername(username: String): Boolean
    fun existsByEmail(email: String): Boolean
}