package hs.kr.casper.configserver.adapter.out.persistence.env

import hs.kr.casper.configserver.adapter.out.persistence.env.repository.EnvironmentConfigurationRepository
import hs.kr.casper.configserver.application.env.port.out.RemoveConfigurationPort
import hs.kr.casper.configserver.application.env.port.out.RetrieveConfigurationPort
import hs.kr.casper.configserver.application.env.port.out.StoreConfigurationPort
import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity
import org.springframework.stereotype.Component

@Component
class EnvironmentConfigurationAdapter(
    private val environmentConfigurationRepository: EnvironmentConfigurationRepository,
    private val environmentConfigurationMapper: EnvironmentConfigurationMapper
): RemoveConfigurationPort, RetrieveConfigurationPort, StoreConfigurationPort{
    override fun removeConfiguration(
        application: String,
        profile: String,
        label: String,
        key: String
    ): EnvironmentConfigurationJpaEntity {
        TODO("Not yet implemented")
    }

    override fun removeConfigurations(
        application: String,
        profile: String,
        label: String
    ): List<EnvironmentConfigurationJpaEntity> {
        TODO("Not yet implemented")
    }

    override fun retrieveConfiguration(
        application: String,
        profile: String,
        label: String,
        key: String
    ): EnvironmentConfigurationJpaEntity {
        TODO("Not yet implemented")
    }

    override fun retrieveConfigurations(
        application: String,
        profile: String,
        label: String
    ): List<EnvironmentConfigurationJpaEntity> {
        TODO("Not yet implemented")
    }

    override fun storeConfiguration(
        application: String,
        profile: String,
        label: String,
        key: String,
        value: String
    ): EnvironmentConfigurationJpaEntity {
        TODO("Not yet implemented")
    }

    override fun storeConfigurations(
        application: String,
        profile: String,
        label: String,
        configurations: Map<String, String>
    ): List<EnvironmentConfigurationJpaEntity> {
        TODO("Not yet implemented")
    }
}