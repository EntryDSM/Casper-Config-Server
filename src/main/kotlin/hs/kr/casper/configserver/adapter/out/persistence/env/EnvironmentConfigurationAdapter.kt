package hs.kr.casper.configserver.adapter.out.persistence.env

import hs.kr.casper.configserver.adapter.out.persistence.env.repository.EnvironmentConfigurationRepository
import hs.kr.casper.configserver.application.env.port.out.ExistsConfigurationPort
import hs.kr.casper.configserver.application.env.port.out.RemoveConfigurationPort
import hs.kr.casper.configserver.application.env.port.out.RetrieveConfigurationPort
import hs.kr.casper.configserver.application.env.port.out.StoreConfigurationPort
import hs.kr.casper.configserver.application.env.port.out.UpdateConfigurationPort
import hs.kr.casper.configserver.domain.env.model.EnvironmentConfiguration
import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity
import org.springframework.stereotype.Component

@Component
class EnvironmentConfigurationAdapter(
    private val environmentConfigurationRepository: EnvironmentConfigurationRepository,
    private val environmentConfigurationMapper: EnvironmentConfigurationMapper
): RemoveConfigurationPort, RetrieveConfigurationPort, StoreConfigurationPort, UpdateConfigurationPort, ExistsConfigurationPort {

    override fun storeConfiguration(
        configuration: EnvironmentConfiguration
    ): EnvironmentConfigurationJpaEntity {
        return environmentConfigurationRepository.save(
            environmentConfigurationMapper.toEntity(configuration)
        )
    }

    override fun existsConfiguration(
        application: String,
        profile: String,
        label: String,
        key: String
    ): Boolean {
        return environmentConfigurationRepository.existsAllByApplicationAndProfileAndLabelAndKey(
            application,
            profile,
            label,
            key
        )
    }

    override fun retrieveConfiguration(
        application: String,
        profile: String,
        label: String,
        key: String
    ): Map<String, String> {
        return environmentConfigurationRepository.findByApplicationAndProfileAndLabelAndKey(
            application,
            profile,
            label,
            key
        ).associate { it.key to it.value }.toMap()
    }

    override fun retrieveConfigurations(
        application: String,
        profile: String,
        label: String
    ): Map<String, String> {
        return environmentConfigurationRepository.findByApplicationAndProfileAndLabel(
            application,
            profile,
            label
        ).associate { it.key to it.value }
    }

    override fun updateConfiguration(
        configuration: EnvironmentConfiguration
    ): EnvironmentConfigurationJpaEntity {
        return environmentConfigurationRepository.save(
            environmentConfigurationMapper.toEntity(configuration)
        )
    }

    override fun removeConfiguration(
        configuration: EnvironmentConfiguration
    ): EnvironmentConfigurationJpaEntity {
         environmentConfigurationRepository.deleteByApplicationAndProfileAndLabelAndKey(
            configuration.application,
            configuration.profile,
            configuration.label,
            configuration.key
        )
        return environmentConfigurationMapper.toEntity(configuration)
    }

    override fun removeConfigurations(
        application: String,
        profile: String,
        label: String
    ): Map<String, String> {
        val configurations = retrieveConfigurations(application, profile, label)
        configurations.keys.forEach { key ->
            removeConfiguration(
                EnvironmentConfiguration(
                    application = application,
                    profile = profile,
                    label = label,
                    key = key,
                    value = configurations.getValue(key),
                )
            )
        }
        return configurations
    }
}