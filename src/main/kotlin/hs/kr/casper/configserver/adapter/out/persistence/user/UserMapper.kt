package hs.kr.casper.configserver.adapter.out.persistence.user

import hs.kr.casper.configserver.domain.user.model.User
import hs.kr.casper.configserver.infrastructure.persistence.user.UserJpaEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface UserMapper {
    fun toDomain(entity: UserJpaEntity): User
    fun toEntity(domain: User): UserJpaEntity
}