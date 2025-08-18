package hs.kr.casper.configserver.adapter.out.persistence.env

import hs.kr.casper.configserver.domain.env.model.EnvironmentConfiguration
import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface EnvironmentConfigurationMapper {
    @Mapping(source = "user", target = "createdBy")
    fun toEntity(domain: EnvironmentConfiguration): EnvironmentConfigurationJpaEntity
    
    @Mapping(source = "createdBy", target = "user")
    fun toDomain(entity: EnvironmentConfigurationJpaEntity): EnvironmentConfiguration
}