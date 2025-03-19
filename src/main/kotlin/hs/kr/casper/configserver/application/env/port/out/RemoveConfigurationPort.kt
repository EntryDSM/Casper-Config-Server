package hs.kr.casper.configserver.application.env.port.out

import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity

interface RemoveConfigurationPort {
    fun removeConfiguration(application: String, profile: String, label: String, key: String): EnvironmentConfigurationJpaEntity

    fun removeConfigurations(application: String, profile: String, label: String): List<EnvironmentConfigurationJpaEntity>
}