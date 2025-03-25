package hs.kr.casper.configserver.adapter.out.persistence.env

import hs.kr.casper.configserver.domain.env.model.EnvironmentConfiguration
import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface EnvironmentConfigurationMapper {
    fun toEntity(domain: EnvironmentConfiguration): EnvironmentConfigurationJpaEntity
    fun toDomain(entity: EnvironmentConfigurationJpaEntity): EnvironmentConfiguration
}