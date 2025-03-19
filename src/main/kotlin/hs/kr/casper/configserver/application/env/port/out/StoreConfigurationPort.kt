package hs.kr.casper.configserver.application.env.port.out

import hs.kr.casper.configserver.infrastructure.persistence.env.EnvironmentConfigurationJpaEntity

interface StoreConfigurationPort {
    fun storeConfiguration(application: String, profile: String, label: String, key: String, value: String): EnvironmentConfigurationJpaEntity

    fun storeConfigurations(application: String, profile: String, label: String, configurations: Map<String, String>): List<EnvironmentConfigurationJpaEntity>
}